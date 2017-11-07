package cn.ego.service;

import cn.ego.utils.EgoResult;

/**
 * 商品详细参数的操作接口
 * @author 起灬風了
 *
 */
public interface ItemParamItemService {
	/**
	 * 根据商品ID查询出相应的参数详情
	 * @param cid-商品ID
	 * @return
	 */
	EgoResult findItemParamItemByItemid(Long cid);
}
