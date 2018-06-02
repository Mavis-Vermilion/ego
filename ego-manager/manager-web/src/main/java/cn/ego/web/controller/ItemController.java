package cn.ego.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.service.ItemService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ItemVo;
/**
 * 对商品操作的控制器
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/rest/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 分页检索商品条目
	 * 
	 * @param page
	 * @param itemVo
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public DatagridModel<ItemVo> findByPage(PageBean page, ItemVo itemVo) {
		return itemService.findByPage(page, itemVo);
	}

	/**
	 * 商品上架
	 * 
	 * @param ids
	 */
	@RequestMapping("/reshelf")
	@ResponseBody
	public Map<String, String> reshelf(Long[] ids) {
		Map<String, String> map = new HashMap<>();
		try {
			itemService.updateStatus(ids, (byte) 1);
			map.put("status", "200");

		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	/**
	 * 商品下架
	 * 
	 * @param ids
	 */
	@RequestMapping("/instock")
	@ResponseBody
	public Map<String, String> instock(Long[] ids) {
		Map<String, String> map = new HashMap<>();
		try {
			itemService.updateStatus(ids, (byte) 2);
			map.put("status", "200");

		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	/**
	 * 假删除商品<br>
	 * 修改商品状态为3
	 * 
	 * @param ids
	 */
	@RequestMapping("/deleteItem")
	@ResponseBody
	public Map<String, String> deleteItem(Long[] ids) {
		Map<String, String> map = new HashMap<>();
		try {
			itemService.updateStatus(ids, (byte) 3);
			map.put("status", "200");

		} catch (Exception e) {
			throw e;
		}
		return map;
	}
	
	/**
	 * 删除商品对应的信息,包括商品的基本信息,描述信息以及商品详细的参数
	 * @param ids-商品id对应的数组
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult delete(Long[] ids) {
		return itemService.deleteItems(ids);
	}

	/**
	 * 添加商品
	 * @param itemVo
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult addItem(ItemVo itemVo, String desc, String itemParams) {
		return itemService.addItem(itemVo, desc, itemParams);
	}
	
	/**
	 * 修改商品信息
	 * @param itemVo 商品的基本信息
	 * @param desc 商品的描述
	 * @param itemParams 商品的详细参数
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public EgoResult updateItem(ItemVo itemVo, String desc, String itemParams) {
		return itemService.updateItem(itemVo,desc,itemParams);
	}
}
