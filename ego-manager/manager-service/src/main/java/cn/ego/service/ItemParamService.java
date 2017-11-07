package cn.ego.service;

import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ItemParamVo;

/**
 * 商品的参数模板操作业务接口
 * 
 * @author 起灬風了
 *
 */
public interface ItemParamService {

	/**
	 * 条件检索并分页查询出所有的商品参数模板
	 * 
	 * @param page
	 *            分页信息
	 * @param itemParamVo
	 *            检索条件的参数
	 * @return
	 */
	DatagridModel<ItemParamVo> findByPage(PageBean page, ItemParamVo itemParamVo);

	/**
	 * 根据商品种类的ID查询对应的模板信息
	 * 
	 * @param cid-商品种类的ID
	 * @return
	 */
	public EgoResult findByCid(Long cid);

	/**
	 * 添加商品模板
	 * 
	 * @param cid-商品种类的ID
	 * @param paramData
	 *            模板信息
	 * @return
	 */
	EgoResult addItemParam(Long cid, String paramData);
}
