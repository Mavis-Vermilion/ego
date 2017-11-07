package cn.ego.rpc.service.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemDescMapper;
import cn.ego.pojo.TbItemDesc;
import cn.ego.rpc.dao.JedisDao;
import cn.ego.rpc.service.ItemDescService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.JsonUtils;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JedisDao jedisDao;
	@Value("${ITEM_DESC}")
	private String ITEM_DESC;
	@Value("${ITEM_TIMEOUT}")
	private Integer ITEM_TIMEOUT;

	@Override
	public EgoResult findItemDescByItemId(Long itemId) {
		// 查询缓存数据库
		try {
			String json = jedisDao.get(ITEM_DESC + itemId);
			if (StringUtils.isNotBlank(json))
				return EgoResult.ok(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		// 将数据添加到缓存数据库
		try {
			if (Objects.nonNull(itemDesc)) {
				String json = JsonUtils.objectToJson(itemDesc);
				jedisDao.set(ITEM_DESC + itemId, json);
				jedisDao.expire(ITEM_DESC + itemId, ITEM_TIMEOUT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EgoResult.ok(itemDesc);
	}

}
