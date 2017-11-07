package cn.ego.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemDescMapper;
import cn.ego.pojo.TbItemDesc;
import cn.ego.service.ItemDescService;
import cn.ego.utils.EgoResult;

/**
 * 商品的描述操作业务接口
 * 
 * @author 起灬風了
 *
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private TbItemDescMapper itemDescMapper;

	/**
	 * 根据itemid查询出描述信息
	 * 
	 * @param itemid
	 *            数据库的主键
	 * @return
	 */
	@Override
	public EgoResult findItemDescById(Long itemid) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemid);
		return new EgoResult(itemDesc);
	}

}
