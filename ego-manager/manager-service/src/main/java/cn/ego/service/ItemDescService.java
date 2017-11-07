package cn.ego.service;

import cn.ego.utils.EgoResult;
/**
 * 商品的描述操作业务接口
 * @author 起灬風了
 *
 */
public interface ItemDescService {
	/**
	 * 根据itemid查询出描述信息
	 * @param itemid 数据库的主键
	 * @return
	 */
	EgoResult findItemDescById(Long itemid);
}
