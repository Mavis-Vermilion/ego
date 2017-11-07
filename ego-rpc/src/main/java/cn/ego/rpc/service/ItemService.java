package cn.ego.rpc.service;

import cn.ego.utils.EgoResult;
/**
 * 商品查询接口
 * @author 起灬風了
 *
 */
public interface ItemService {

	/**
	 * 根据商品ID查询商品信息
	 * 
	 * @param itemId-商品id
	 * @return
	 */
	EgoResult findItemByItemId(Long itemId);
}
