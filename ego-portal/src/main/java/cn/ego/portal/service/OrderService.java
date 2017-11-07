package cn.ego.portal.service;

import javax.servlet.http.HttpServletRequest;

import cn.ego.utils.EgoResult;
import cn.ego.vo.OrderVo;

public interface OrderService {

	EgoResult addOrder(OrderVo orderVo, HttpServletRequest request);

	
}
