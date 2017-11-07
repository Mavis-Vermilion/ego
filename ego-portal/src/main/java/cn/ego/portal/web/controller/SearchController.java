package cn.ego.portal.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.ego.portal.pojo.PageBean;
import cn.ego.portal.service.SearchService;
import cn.ego.utils.EgoResult;
import cn.egoportal.vo.ItemInfoVo;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	/**
	 * 商品的搜索服务
	 * @param query-搜索条件
	 * @param page-当前页
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String search(@RequestParam(value="q",required=true) String query ,
			@RequestParam(value="page",defaultValue="1") Integer page,
			Model model) {
		try {
			query=new String(query.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		EgoResult egoResult = searchService.search(query,page);
		
		PageBean pageBean = (PageBean) egoResult.getData();
		//List<ItemInfoVo> itemList = JsonUtils.jsonToList(pageBean.getData().toString(), ItemInfoVo.class);
		String itemListJSON = JSON.toJSONString(pageBean.getData());
		List<ItemInfoVo> itemList = JSONArray.parseArray(itemListJSON, ItemInfoVo.class);
		model.addAttribute("itemList", itemList);
		model.addAttribute("query", query);
		model.addAttribute("totalPages", pageBean.getTotalPage());
		model.addAttribute("page", pageBean.getCurrentPage());
		return "search";
	}
}
