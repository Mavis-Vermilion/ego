package cn.ego.vo;

import java.util.ArrayList;
import java.util.List;

import cn.ego.pojo.TbOrder;
import cn.ego.pojo.TbOrderItem;
import cn.ego.pojo.TbOrderShipping;

public class OrderVo extends TbOrder {

	private List<TbOrderItem> orderItems = new ArrayList<>();

	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
