package cn.ego.rpc.service.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemMapper;
import cn.ego.pojo.TbItem;
import cn.ego.rpc.dao.JedisDao;
import cn.ego.rpc.service.ItemService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.JsonUtils;
import cn.ego.vo.ItemVo;

/**
 * 商品查询实现类
 * @author 起灬風了
 *
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private JedisDao jedisDao;
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${ITEM_TIMEOUT}")
	private Integer ITEM_TIMEOUT;
	/**
	 * 根据商品ID查询商品信息
	 * 
	 * @param itemId-商品id
	 * @return
	 */
	@Override
	public EgoResult findItemByItemId(Long itemId) {
		//查询缓存数据库
		try {
			String json = jedisDao.get(ITEM_INFO+itemId);
			if(StringUtils.isNotBlank(json))
				return EgoResult.ok(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		ItemVo itemVo = new ItemVo();
		BeanUtils.copyProperties(tbItem, itemVo);
		
		//将数据添加到缓存数据库
		try {
			if(Objects.nonNull(itemVo)) {
				String json = JsonUtils.objectToJson(itemVo);
				jedisDao.set(ITEM_INFO+itemId, json);
				jedisDao.expire(ITEM_INFO+itemId, ITEM_TIMEOUT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return EgoResult.ok(itemVo);
	}

}
