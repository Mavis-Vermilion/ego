package cn.ego.rpc.service;

import cn.ego.rpc.pojo.ItemCatResult;

/**
 * 首页导航菜单数据访问接口
 * @author 起灬風了
 *
 */
public interface ItemCatService {

	/**
	 * 查询首页左侧所有的菜单
	 * @return
	 */
	ItemCatResult findAll();
}
