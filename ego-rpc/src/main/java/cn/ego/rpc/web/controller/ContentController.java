package cn.ego.rpc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.rpc.service.ContentService;
import cn.ego.utils.EgoResult;

/**
 * 首页内容访问的控制器
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/rest/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * 根据首页栏位的id查询出对应的广告位数据
	 * @param categoryid 首页栏位的id
	 * @return
	 */
	@RequestMapping("/query/{categoryid}")
	@ResponseBody
	public String findByCategoryId(@PathVariable Long categoryid) {
		
		return contentService.findByCategoryId(categoryid);
	}
	
	/**
	 * 清除首页模块的缓存
	 * @param categoryid
	 * @return
	 */
	@RequestMapping("/clear/{categoryid}")
	@ResponseBody
	public EgoResult clearCache(@PathVariable Long categoryid) {
		return contentService.clearCache(categoryid);
	}
}
