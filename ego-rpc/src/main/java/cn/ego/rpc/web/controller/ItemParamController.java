package cn.ego.rpc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.rpc.service.ItemParamService;
import cn.ego.utils.EgoResult;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/itemParam")
	@ResponseBody
	public EgoResult findItemParamByItemId(Long itemId) {
		return itemParamService.findItemParamByItemId(itemId);
	}
}
