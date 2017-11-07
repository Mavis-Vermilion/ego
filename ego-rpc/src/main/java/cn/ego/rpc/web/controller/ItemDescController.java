package cn.ego.rpc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.rpc.service.ItemDescService;
import cn.ego.utils.EgoResult;

@Controller
public class ItemDescController {

	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/itemDesc")
	@ResponseBody
	public EgoResult findItemDescByItemId(Long itemId) {
		return itemDescService.findItemDescByItemId(itemId);
	}
} 
