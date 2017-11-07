package cn.ego.portal.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ego.portal.service.CartService;
import cn.egoportal.vo.CartItemCustomer;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addItem2Cart(@PathVariable Long itemId,@RequestParam(defaultValue="1") Integer num,HttpServletRequest request,HttpServletResponse response) {
		cartService.addItem2Cart(itemId, num, request, response);
		return "redirect:/cart/cartSuccess.html";
	}
	
	@RequestMapping("/cartSuccess")
	public String showCartSuccessUI() {
		return "cartSuccess";
	}
	
	@RequestMapping("/cart")
	public String showCartUI(HttpServletRequest request,HttpServletResponse response) {
		List<CartItemCustomer> cartList = cartService.showCart(request);
		request.setAttribute("cartList", cartList);
		return "cart";
	}
}
