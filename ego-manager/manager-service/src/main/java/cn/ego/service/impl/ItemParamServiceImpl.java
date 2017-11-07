package cn.ego.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ego.mapper.TbItemParamMapper;
import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.pojo.TbItemParam;
import cn.ego.pojo.TbItemParamExample;
import cn.ego.pojo.TbItemParamExample.Criteria;
import cn.ego.service.ItemParamService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ItemParamVo;

/**
 * 商品的参数模板操作业务实现类
 * 
 * @author 起灬風了
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	/**
	 * 条件检索并分页查询出所有的商品参数模板
	 * 
	 * @param page
	 *            分页信息
	 * @param itemParamVo
	 *            检索条件的参数
	 * @return
	 */
	@Override
	public DatagridModel<ItemParamVo> findByPage(PageBean page, ItemParamVo itemParamVo) {

		PageHelper.startPage(page.getPage(), page.getRows());
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(null);

		PageInfo<TbItemParam> info = new PageInfo<>(list);

		DatagridModel<ItemParamVo> data = new DatagridModel<>();
		data.setTotal(info.getTotal());

		List<ItemParamVo> volist = null;
		if (null != list && list.size() > 0) {
			ItemParamVo vo = new ItemParamVo();
			volist = new ArrayList<>();
			for (TbItemParam tbItemParam : list) {
				ItemParamVo clone = (ItemParamVo) vo.clone();
				BeanUtils.copyProperties(tbItemParam, clone);
				volist.add(clone);
			}
		}
		data.setRows(volist);
		return data;
	}

	/**
	 * 根据商品种类的ID查询对应的模板信息
	 * 
	 * @param cid-商品种类的ID
	 * @return
	 */
	@Override
	public EgoResult findByCid(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (null != list && list.size() > 0) {
			return EgoResult.ok(list.get(0));
		}
		return EgoResult.ok();
	}

	/**
	 * 添加商品模板
	 * 
	 * @param cid-商品种类的ID
	 * @param paramData
	 *            模板信息
	 * @return
	 */
	@Override
	public EgoResult addItemParam(Long cid, String paramData) {
		Date date = new Date();
		TbItemParam itemParam = new TbItemParam(cid, date, date, paramData);
		itemParamMapper.insert(itemParam);
		return EgoResult.ok();
	}

}
