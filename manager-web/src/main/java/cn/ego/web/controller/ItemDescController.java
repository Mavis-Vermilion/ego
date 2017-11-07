package cn.ego.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.service.ItemDescService;
import cn.ego.utils.EgoResult;
/**
 * 商品的描述控制器
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/rest/itemDesc")
public class ItemDescController {
	//rest/item/query/item/desc/'+data.id
	@Autowired
	private ItemDescService itemDescService;
	/**
	 * 根据商品ID查询出对应的描述信
	 * @param itemid-商品ID息
	 * @return
	 */
	@RequestMapping("/query/{itemid}")
	@ResponseBody
	public EgoResult findItemDescById(@PathVariable("itemid") Long itemid) {
		return itemDescService.findItemDescById(itemid);
	}
}
