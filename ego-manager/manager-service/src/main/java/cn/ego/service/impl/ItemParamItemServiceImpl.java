package cn.ego.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemParamItemMapper;
import cn.ego.pojo.TbItemParamItem;
import cn.ego.pojo.TbItemParamItemExample;
import cn.ego.pojo.TbItemParamItemExample.Criteria;
import cn.ego.service.ItemParamItemService;
import cn.ego.utils.EgoResult;

/**
 * 商品详细参数的操作实现类
 * 
 * @author 起灬風了
 *
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 根据商品ID查询出相应的参数详情
	 * 
	 * @param cid-商品ID
	 * @return
	 */
	@Override
	public EgoResult findItemParamItemByItemid(Long cid) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(cid);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (null != list && !list.isEmpty())
			return EgoResult.ok(list.get(0));
		return EgoResult.ok();
	}

}
