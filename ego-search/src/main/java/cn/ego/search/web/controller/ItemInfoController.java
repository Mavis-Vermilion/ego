package cn.ego.search.web.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.search.service.ItemInfoService;
import cn.ego.search.vo.PageBean;
import cn.ego.utils.EgoResult;

@Controller
@RequestMapping("/itemInfo")
public class ItemInfoController {

	@Autowired
	private ItemInfoService itemInfoService;

	/**
	 * 将数据库信息添加到solr的索引库
	 * 
	 * @return
	 */
	@RequestMapping("/import")
	@ResponseBody
	public EgoResult importItemInfo() {
		return itemInfoService.importItemInfo();
	}

	/**
	 * 分页检索输入的信息
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页条数
	 * @param query
	 *            检索条件
	 * @return
	 */
	@RequestMapping("/searchIteminfo")
	@ResponseBody
	public EgoResult searchIteminfo(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows,
			@RequestParam(value = "query", required = true) String query) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		try {
			query = new String(query.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return itemInfoService.searchIteminfo(pageBean, query);
	}
}
