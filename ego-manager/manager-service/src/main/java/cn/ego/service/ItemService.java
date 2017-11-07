package cn.ego.service;

import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ItemVo;
/**
 * 商品条目业务操作接口
 * @author 起灬風了
 *
 */
public interface ItemService {

	/**
	 * 分页查询商品条目
	 * @param page分页条件
	 * @param itemVo检索条件
	 * @return
	 */
	DatagridModel<ItemVo> findByPage(PageBean page,ItemVo itemVo);

	/**
	 * 修改商品状态
	 * <br>
	 * 商品状态，1-正常，2-下架，3-删除
	 */
	void updateStatus(Long[] ids, byte status);
	
	/**
	 * 根据商品id查询商品信息
	 * @param id
	 * @return
	 */
	ItemVo findById(Long id);
	
	/**
	 * 删除商品对应的信息,包括商品的基本信息,描述信息以及商品详细的参数
	 * @param ids-商品id对应的数组
	 * @return
	 */
	EgoResult deleteItems(Long[] ids);

	/**
	 * 添加商品
	 * @param itemVo 商品的基本信息
	 * @param desc 商品的描述信息
	 * @param paramData 商品的规格参数
	 * @return
	 */
	EgoResult addItem(ItemVo itemVo, String desc ,String paramData);

	/**
	 * 修改商品
	 * @param itemVo 商品的基本信息
	 * @param desc 商品的描述信息
	 * @param paramData 商品的规格参数
	 * @return
	 */
	EgoResult updateItem(ItemVo itemVo, String desc, String paramData);
}
