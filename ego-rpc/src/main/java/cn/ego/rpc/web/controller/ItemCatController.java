package cn.ego.rpc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.rpc.pojo.ItemCatResult;
import cn.ego.rpc.service.ItemCatService;
import cn.ego.utils.JsonUtils;

/**
 * 
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * jsonp跨域请求
	 * @param callback-jsonp跨域请求的回调参数
	 * @return 首页左侧菜单的json串数据
	 */
	@RequestMapping(value="/all",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String findAll(String callback) {
		ItemCatResult result = itemCatService.findAll();
		String str = JsonUtils.objectToJson(result);
		return callback+"(" +str+");";
	}
}
