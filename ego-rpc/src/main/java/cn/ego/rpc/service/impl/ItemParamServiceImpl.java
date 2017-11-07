package cn.ego.rpc.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemParamItemMapper;
import cn.ego.pojo.TbItemParamItem;
import cn.ego.pojo.TbItemParamItemExample;
import cn.ego.pojo.TbItemParamItemExample.Criteria;
import cn.ego.rpc.dao.JedisDao;
import cn.ego.rpc.service.ItemParamService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.JsonUtils;
@Service
public class ItemParamServiceImpl implements ItemParamService{

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private JedisDao jedisDao;
	@Value("${ITEM_PARAM}")
	private String ITEM_PARAM;
	@Value("${ITEM_TIMEOUT}")
	private Integer ITEM_TIMEOUT;
	
	@Override
	public EgoResult findItemParamByItemId(Long itemId) {
		// 查询缓存数据库
		try {
			String json = jedisDao.get(ITEM_PARAM + itemId);
			if (StringUtils.isNotBlank(json))
				return EgoResult.ok(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		TbItemParamItem param = null;
		if(!list.isEmpty()) {
			param = list.get(0);
		}
		// 将数据添加到缓存数据库
		try {
			if (Objects.nonNull(param)) {
				String json = JsonUtils.objectToJson(param);
				jedisDao.set(ITEM_PARAM + itemId, json);
				jedisDao.expire(ITEM_PARAM + itemId, ITEM_TIMEOUT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EgoResult.ok(param);
	}

}
