package cn.ego.portal.service.impl;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.portal.service.OrderService;
import cn.ego.portal.service.UserService;
import cn.ego.portal.utils.CookieUtils;
import cn.ego.utils.EgoResult;
import cn.ego.utils.HttpClientUtil;
import cn.ego.utils.JsonUtils;
import cn.ego.vo.OrderVo;
import cn.ego.vo.UserVo;
@Service
public class OrderServiceImpl implements OrderService{

	@Value("${COOKIE_TOKEN}")
	private String COOKIE_TOKEN;
	@Autowired
	private UserService userService;
	@Value("${ORDER_URL}")
	private String ORDER_URL;
	@Value("${ORDER_CREATE_ADDRESS}")
	private String ORDER_CREATE_ADDRESS;
	@Override
	public EgoResult addOrder(OrderVo orderVo,HttpServletRequest request) {
		String userToken = CookieUtils.getCookieValue(request, COOKIE_TOKEN);
		UserVo userVo = userService.findUserByToken(userToken);
		if(Objects.isNull(userVo))
			return EgoResult.build(400, "未登录");
		orderVo.setUserId(userVo.getId());
		String json = JsonUtils.objectToJson(orderVo);
		String egoJSON = HttpClientUtil.doPostJson(ORDER_URL+ORDER_CREATE_ADDRESS, json);
		EgoResult egoResult = EgoResult.format(egoJSON);
		//EgoResult parseObject = JSON.parseObject(egoJSON, EgoResult.class);
		return egoResult;
	}

}
