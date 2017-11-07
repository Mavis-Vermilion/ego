package cn.ego.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbContentCategoryMapper;
import cn.ego.pojo.EasyUITreeModel;
import cn.ego.pojo.TbContentCategory;
import cn.ego.pojo.TbContentCategoryExample;
import cn.ego.pojo.TbContentCategoryExample.Criteria;
import cn.ego.service.ContentCategoryService;
import cn.ego.utils.EgoResult;

/**
 * 首页内容分类管理的业务实现类
 * @author 起灬風了
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	/**
	 * 根据parentId查询出下级树形菜单的信息
	 * @param parentId
	 * @return
	 */
	@Override
	public List<EasyUITreeModel> findByParentId(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if(null != list && list.size()>0) {
			List<EasyUITreeModel> model = new ArrayList<>(list.size());
			for (TbContentCategory tbContentCategory : list) {
				model.add(new EasyUITreeModel(tbContentCategory.getId(), tbContentCategory.getName(), tbContentCategory.getIsParent()));
			}
			return model;
		}
		return null;
	}

	/**
	 * 在树形菜单上添加节点
	 * @param parentId 父节点的id
	 * @param name 要添加节点的内容
	 * @return
	 */
	@Override
	public EgoResult addContentCategoryNode(Long parentId, String name) {
		Date date = new Date();
		TbContentCategory category = new TbContentCategory(parentId, name, 1, 1, false, date, date);
		long id = contentCategoryMapper.insert(category);
		System.out.println(category.getId()+"-----------");
		category.setId(id);
		//查询出要添加节点的上级节点是否为一个父节点,如果不是讲上级节点变成父节点
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent() && parentNode!= null) {
			parentNode.setIsParent(true);
			parentNode.setUpdated(date);
			contentCategoryMapper.updateByPrimaryKeySelective(parentNode);
		}
		return EgoResult.ok(category);
	}

	/**
	 * 修改节点信息
	 * @param id 要修改节点的ID
	 * @param name 要修改节点的内容
	 * @return
	 */
	@Override
	public EgoResult updateContentCategoryNode(Long id, String name) {
		TbContentCategory category = new TbContentCategory();
		category.setId(id);
		category.setName(name);
		category.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(category);
		return EgoResult.ok();
	}

}
