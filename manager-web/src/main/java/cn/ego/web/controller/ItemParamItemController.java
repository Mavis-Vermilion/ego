package cn.ego.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.service.ItemParamItemService;
import cn.ego.utils.EgoResult;

/**
 * 商品详细参数的控制器
 * 
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/rest/item/param/item")
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	/**
	 * 根据商品ID查询出相应的参数详情
	 * @param cid-商品ID
	 * @return
	 */
	@RequestMapping("/query/{cid}")
	@ResponseBody
	public EgoResult findItemParamItemByItemid(@PathVariable("cid") Long cid) {
		return itemParamItemService.findItemParamItemByItemid(cid);
	}
}
