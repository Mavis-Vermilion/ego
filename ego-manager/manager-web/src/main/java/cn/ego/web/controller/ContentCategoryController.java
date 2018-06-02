package cn.ego.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.pojo.EasyUITreeModel;
import cn.ego.service.ContentCategoryService;
import cn.ego.utils.EgoResult;

/**
 * 首页布局
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 分页查询出首页的栏目并以树形菜单展示出来
	 * @param parentId父节点ID
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeModel> findByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.findByParentId(parentId);
	}

	/**
	 * 添加首页模块节点
	 * @param parentId 父节点ID
	 * @param name 
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public EgoResult addContentCategoryNode(Long parentId, String name) {
		return contentCategoryService.addContentCategoryNode(parentId, name);
	}

	/**
	 * 修改模块的信息
	 * @param id模块的ID
	 * @param name修改之后的name
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public EgoResult updateContentCategoryNode(Long id, String name) {
		return contentCategoryService.updateContentCategoryNode(id, name);
	}
}
