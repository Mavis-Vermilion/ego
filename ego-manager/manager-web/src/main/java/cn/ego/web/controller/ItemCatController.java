package cn.ego.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.pojo.EasyUITreeModel;
import cn.ego.service.ItemCatService;
/**
 * 商品的种类管理
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 根据父ID查询出商品的分类
	 * @param parentId 默认为0
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeModel> findCatByParentId(@RequestParam(value="id",defaultValue="0") Long parentId){
		return itemCatService.findCatByParentId(parentId);
	}
}
