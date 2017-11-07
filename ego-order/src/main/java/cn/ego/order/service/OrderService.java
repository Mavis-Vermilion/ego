package cn.ego.order.service;

import java.util.List;

import cn.ego.pojo.TbOrder;
import cn.ego.pojo.TbOrderItem;
import cn.ego.pojo.TbOrderShipping;
import cn.ego.utils.EgoResult;

public interface OrderService {

	/**
	 * 
	 * @param order-订单详情
	 * @param orderItem-购买的商品
	 * @param orderShipping-收货详情
	 * @return
	 */
	EgoResult addOrder(TbOrder order,List<TbOrderItem> orderItems,TbOrderShipping orderShipping) throws Exception;
}
