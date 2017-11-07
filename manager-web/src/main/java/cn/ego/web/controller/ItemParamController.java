package cn.ego.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.service.ItemParamService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ItemParamVo;

/**
 * 商品参数模板的控制器
 * @author 起灬風了
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public DatagridModel<ItemParamVo> findByPage(PageBean page, ItemParamVo itemParamVo) {
		return itemParamService.findByPage(page, itemParamVo);
	}
	/**
	 * 根据商品种类的ID查询出相应的模板信息
	 * @param cid 种类的id
	 * @return
	 */
	@RequestMapping("/query/{cid}")
	@ResponseBody
	public EgoResult findByCid(@PathVariable("cid") Long cid) {
		return itemParamService.findByCid(cid);
	}
	
	/**
	 * 添加商品参数的模板
	 * @param cid 种类的id
	 * @param paramData 
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public EgoResult addItemParam(@PathVariable("cid") Long cid,String paramData) {
		return itemParamService.addItemParam(cid, paramData);
	}
	
}
