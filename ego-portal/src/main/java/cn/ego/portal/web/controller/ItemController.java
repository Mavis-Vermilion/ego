package cn.ego.portal.web.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.pojo.TbItemDesc;
import cn.ego.portal.service.ItemService;
import cn.egoportal.vo.ItemCustomer;
/**
 * 商品详情
 * @author 起灬風了
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	/**
	 * 根据商品ID查询商品信息
	 * @param itemId 商品ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String findItemByItemId(@PathVariable Long itemId,Model model) {
		ItemCustomer itemCustomer = (ItemCustomer) itemService.findItemByItemId(itemId);
		model.addAttribute("item",itemCustomer);
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{itemId}")
	@ResponseBody
	public String findItemDescByItemId(@PathVariable Long itemId) {
		TbItemDesc itemDesc = itemService.findItemDescByItemId(itemId);
		if(Objects.nonNull(itemDesc)) {
			System.out.println(itemDesc.getItemDesc());
			return itemDesc.getItemDesc();
		}
		return null;
	}
	
	@RequestMapping(value="/item/param/{itemId}"/*,produces="application/json;charset=UTF-8"*/)
	@ResponseBody
	public String findItemParamByItemId(@PathVariable Long itemId) {
		String param = itemService.findItemParamByItemId(itemId);
		System.out.println(param);
		return param;
	}
	
}
