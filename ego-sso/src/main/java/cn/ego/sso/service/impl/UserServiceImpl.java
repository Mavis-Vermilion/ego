package cn.ego.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.ego.mapper.TbUserMapper;
import cn.ego.pojo.TbUser;
import cn.ego.pojo.TbUserExample;
import cn.ego.pojo.TbUserExample.Criteria;
import cn.ego.sso.dao.JedisDao;
import cn.ego.sso.service.UserService;
import cn.ego.sso.utils.CookieUtils;
import cn.ego.utils.EgoResult;
import cn.ego.utils.JsonUtils;
import cn.ego.vo.UserVo;

/**
 * 用户操作业务实现类
 * 
 * @author 起灬風了
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisDao jedisDao;
	@Value("${USER_KEY}")
	private String USER_KEY;
	@Value("${USER_KEY_TIMEOUT}")
	private Integer USER_KEY_TIMEOUT;
	@Value("${COOKIE_TOKEN}")
	private String COOKIE_TOKEN;
	@Override
	public EgoResult userCheck(String param, Integer type) {
		if (StringUtils.isBlank(param) || Objects.isNull(type))
			return new EgoResult(400, "非法的参数异常", false);
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		switch (type) {
		case 1:
			criteria.andUsernameEqualTo(param);
			break;
		case 2:
			criteria.andPhoneEqualTo(param);
			break;
		case 3:
			criteria.andEmailEqualTo(param);
			break;
		default:
			return new EgoResult(400, "不支持的类型", false);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return EgoResult.ok(true);
		return EgoResult.ok(false);
	}

	@Override
	public EgoResult userLogin(UserVo userVo,HttpServletRequest request,HttpServletResponse response) {
		if (Objects.isNull(userVo))
			return EgoResult.build(400, "非法的参数异常", "exception");
		// 查询缓存数据库
		/*
		 * try {
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userVo.getUsername());
		List<TbUser> list = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return EgoResult.build(400, "username is non-exist", "用户名不存在");

		TbUser user = list.get(0);
		String md5Hex = DigestUtils.md5Hex(userVo.getPassword());
		if (!StringUtils.equals(md5Hex, user.getPassword()))
			return EgoResult.build(400, "password error", "密码错误");
		String uuid = UUID.randomUUID().toString();
		user.setPassword(null);
		String json = JsonUtils.objectToJson(user);
		// 验证成功将数据存储到redis
		try {
			jedisDao.set(USER_KEY + uuid, json);
			jedisDao.expire(USER_KEY + uuid, USER_KEY_TIMEOUT);
			CookieUtils.setCookie(request, response, COOKIE_TOKEN, uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return EgoResult.ok(uuid);
	}

	@Override
	public EgoResult userToken(String token) {
		String json = jedisDao.get(USER_KEY + token);
		if (StringUtils.isBlank(json))
			return EgoResult.build(400, "token error", "错误的令牌");
		UserVo userVo = JsonUtils.jsonToPojo(json, UserVo.class);
		return EgoResult.ok(userVo);
	}

	@Override
	public EgoResult userRegister(UserVo userVo) {
		try {
			String md5Hex = DigestUtils.md5Hex(userVo.getPassword());
			userVo.setPassword(md5Hex);
			Date date = new Date();
			userVo.setCreated(date);
			userVo.setUpdated(date);
			userMapper.insert(userVo);
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400, "注册失败. 请校验数据后请再提交数据.");
		}

	}

	@Override
	public EgoResult userLogout(String token) {
		Long del = jedisDao.del(USER_KEY + token);
		if(del>0)
			return EgoResult.ok("注销成功");
		else
			return EgoResult.build(400, "不存在的令牌");
		//return EgoResult.build(400, "未知的错误");
	}

}
