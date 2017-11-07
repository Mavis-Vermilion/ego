package cn.ego.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemCatMapper;
import cn.ego.pojo.EasyUITreeModel;
import cn.ego.pojo.TbItemCat;
import cn.ego.pojo.TbItemCatExample;
import cn.ego.pojo.TbItemCatExample.Criteria;
import cn.ego.service.ItemCatService;

/**
 * 商品种类操作的业务实现类
 * @author 起灬風了
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 根据parentId查询出下面的子节点
	 * @param parentId
	 * @return
	 */
	@Override
	public List<EasyUITreeModel> findCatByParentId(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		if(null != list && list.size()>0) {
			List<EasyUITreeModel> model = new ArrayList<>(list.size());
			for (TbItemCat tbItemCat : list) {
				model.add(new EasyUITreeModel(tbItemCat.getId(), tbItemCat.getName(), tbItemCat.getIsParent()));
			}
			return model;
		}
			
		return null;
	}
	
	
}
