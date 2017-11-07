package cn.ego.rpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbItemCatMapper;
import cn.ego.pojo.TbItemCat;
import cn.ego.pojo.TbItemCatExample;
import cn.ego.pojo.TbItemCatExample.Criteria;
import cn.ego.rpc.pojo.ItemCatNode;
import cn.ego.rpc.pojo.ItemCatResult;
import cn.ego.rpc.service.ItemCatService;

/**
 * 首页导航菜单数据访问实现类
 * @author 起灬風了
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 查询首页左侧所有的菜单
	 * @return
	 */
	@Override
	public ItemCatResult findAll() {
		List<ItemCatNode> list = this.findByParentId(0L);
		ItemCatResult result = new ItemCatResult();
		result.setData(list);
		return result;
	}

	/**
	 * 递归查询当前id下的所有菜单
	 * @param parentId 查询parentId下的所有菜单
	 * @return
	 */
	private List findByParentId(long parentId) {
		int count = 0;
		List nodes = new ArrayList<>();
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询出当前parentId对应的菜单
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//再次查询出菜单的下级
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				ItemCatNode catNode = new ItemCatNode();
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				if (tbItemCat.getParentId() == 0) {//是否为一级节点
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");

				} else {//不是一级节点
					catNode.setName(tbItemCat.getName());

				}
				//是父节点就查出下级节点
				catNode.setItemCat(findByParentId(tbItemCat.getId()));
				count++;
				if (count > 14)
					break;
				nodes.add(catNode);
			} else {//不是父节点
				String str = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				nodes.add(str);
			}
		}
		return nodes;
	}

}
