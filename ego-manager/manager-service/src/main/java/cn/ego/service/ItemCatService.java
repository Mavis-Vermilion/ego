package cn.ego.service;

import java.util.List;

import cn.ego.pojo.EasyUITreeModel;

/**
 * 商品种类操作的业务接口
 * @author 起灬風了
 *
 */
public interface ItemCatService {

	/**
	 * 根据parentId查询出下面的子节点
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeModel> findCatByParentId(Long parentId);
}
