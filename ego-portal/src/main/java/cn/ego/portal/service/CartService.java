package cn.ego.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ego.utils.EgoResult;
import cn.egoportal.vo.CartItemCustomer;

public interface CartService {

	EgoResult addItem2Cart(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response);
	
	List<CartItemCustomer> showCart(HttpServletRequest request);
}
