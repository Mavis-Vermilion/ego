package cn.ego.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jsp页面跳转的controller
 * @author 起灬風了
 *
 */
@Controller
public class MainController {

	/**
	 * 跳转到index.jsp页面
	 * @return
	 */
	@RequestMapping("/")
	public String showIndexUI() {
		return "index";
	}
	
	
	/**
	 * restful风格
	 * 跳转到指定的jsp页面
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPageUI(@PathVariable String page) {
		return page;
	}
}
