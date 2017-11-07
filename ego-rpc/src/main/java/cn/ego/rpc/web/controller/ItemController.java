package cn.ego.rpc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.rpc.service.ItemService;
import cn.ego.utils.EgoResult;
/**
 * 商品信息的访问接口
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 根据id查询商品信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public EgoResult findItemByItemId(Long itemId) {
		return itemService.findItemByItemId(itemId);
	}
}
