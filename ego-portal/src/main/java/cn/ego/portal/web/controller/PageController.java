package cn.ego.portal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.ego.portal.pojo.ContentData;
import cn.ego.portal.service.ContentDataService;

/**
 * 控制页面跳转的控制器
 * @author 起灬風了
 *
 */
@Controller
public class PageController {

	@Autowired
	private ContentDataService contentDataService;

	/**
	 * 跳转到index.jsp
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndexUI(Model model) {
		List<ContentData> list = contentDataService.findContentData();
		model.addAttribute("ad1", JSON.toJSONString(list));
		return "index";
	}
}
