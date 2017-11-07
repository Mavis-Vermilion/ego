package cn.ego.service;

import java.util.List;

import cn.ego.pojo.EasyUITreeModel;
import cn.ego.utils.EgoResult;

/**
 * 首页内容分类管理的业务接口
 * @author 起灬風了
 *
 */
public interface ContentCategoryService {

	/**
	 * 根据parentId查询出下级树形菜单的信息
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeModel> findByParentId(Long parentId);

	/**
	 * 在树形菜单上添加节点
	 * @param parentId 父节点的id
	 * @param name 要添加节点的内容
	 * @return
	 */
	EgoResult addContentCategoryNode(Long parentId, String name);

	/**
	 * 修改节点信息
	 * @param id 要修改节点的ID
	 * @param name 要修改节点的内容
	 * @return
	 */
	EgoResult updateContentCategoryNode(Long id, String name);

}
