package cn.ego.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.service.ContentService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ContentVo;

/**
 * 首页内容模块
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * 根据模块ID查询出对应的内容信息
	 * @param categoryId
	 * @param page-封装分页参数的javabean
	 * @return
	 */
	// content/query/list
	@RequestMapping("/query/list")
	@ResponseBody
	public DatagridModel<ContentVo> findBycategoryId(
			@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
			PageBean page) {
		return contentService.findBycategoryId(categoryId,page);
	}
	/**
	 * 在相应的模块添加内容
	 * @param contentVo
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult addContent(ContentVo contentVo) {
		return contentService.addContent(contentVo);
	}
	
	/**
	 * 修改模块内容
	 * @param contentVo
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public EgoResult updateContent(ContentVo contentVo) {
		return contentService.updateContent(contentVo);
	}
	
	/**
	 * 删除模块的内容信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult deleteContents(Long[] ids) {
		return contentService.deleteContents(ids);
	}
	
}
