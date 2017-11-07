package cn.ego.portal.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ego.portal.service.CartService;
import cn.ego.portal.service.OrderService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.OrderVo;
import cn.egoportal.vo.CartItemCustomer;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrderCartUI(HttpServletRequest request) {
		List<CartItemCustomer> cartList = cartService.showCart(request);
		request.setAttribute("cartList", cartList);
		return "order-cart";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String orderCreate(OrderVo orderVo,HttpServletRequest request) {
		EgoResult egoResult = orderService.addOrder(orderVo,request);
		if(egoResult.getStatus()==200) {
			Integer orderId = (Integer) egoResult.getData();
			String payment = orderVo.getPayment();
			
			return "redirect:/order/success.html?orderId="+orderId+"&payment="+payment;
		}
		
		return "redirect:/index.html";
	}
	
	@RequestMapping("/success")
	public String  showSuccessUI(Long orderId,String payment,Model model) {
		String date = new DateTime().plusDays(3).toString("yyyy年MM月dd日");
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", payment);
		model.addAttribute("date", date);
		return "success";
	}
}
