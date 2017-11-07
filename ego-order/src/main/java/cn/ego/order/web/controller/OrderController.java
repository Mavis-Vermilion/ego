package cn.ego.order.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.order.service.OrderService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.OrderVo;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	@ResponseBody
	public EgoResult orderCreate(@RequestBody OrderVo orderVo) {
		try {
			return orderService.addOrder(orderVo, orderVo.getOrderItems(), orderVo.getOrderShipping());
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400, "添加失败");
		}
	}
}
