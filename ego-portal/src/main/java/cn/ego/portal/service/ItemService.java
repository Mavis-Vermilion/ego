package cn.ego.portal.service;

import cn.ego.pojo.TbItemDesc;
import cn.ego.vo.ItemVo;

public interface ItemService {

	public ItemVo findItemByItemId(Long itemId);
	
	public TbItemDesc findItemDescByItemId(Long itemId);
	
	public String findItemParamByItemId(Long itemId);
}
