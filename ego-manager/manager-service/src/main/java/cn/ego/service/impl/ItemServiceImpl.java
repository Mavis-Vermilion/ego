package cn.ego.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ego.mapper.TbItemDescMapper;
import cn.ego.mapper.TbItemMapper;
import cn.ego.mapper.TbItemParamItemMapper;
import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.pojo.TbItem;
import cn.ego.pojo.TbItemDesc;
import cn.ego.pojo.TbItemParamItem;
import cn.ego.pojo.TbItemParamItemExample;
import cn.ego.pojo.TbItemParamItemExample.Criteria;
import cn.ego.service.ItemService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.IDUtils;
import cn.ego.vo.ItemVo;

/**
 * 商品条目业务操作实现类
 * 
 * @author 起灬風了
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 分页查询商品条目
	 * 
	 * @param page分页条件
	 * @param itemVo检索条件
	 * @return
	 */
	@Override
	public DatagridModel<ItemVo> findByPage(PageBean page, ItemVo itemVo) {
		PageHelper.startPage(page.getPage(), page.getRows());
		List<TbItem> list = itemMapper.selectByExample(null);
		PageInfo<TbItem> info = new PageInfo<>(list);
		DatagridModel<ItemVo> data = new DatagridModel<>();
		data.setTotal(info.getTotal());
		List<ItemVo> volist = null;
		if (null != list && list.size() > 0) {
			ItemVo vo = new ItemVo();
			volist = new ArrayList<>();
			for (TbItem tbItem : list) {
				ItemVo clone = (ItemVo) vo.clone();
				BeanUtils.copyProperties(tbItem, clone);
				volist.add(clone);
			}
		}
		data.setRows(volist);
		return data;
	}

	/**
	 * 修改商品状态 商品状态，1-正常，2-下架，3-删除
	 */
	@Override
	public void updateStatus(Long[] ids, byte status) {
		if (null == ids || ids.length == 0)
			return;
		switch (status) {
		case 1:
			this.updateItemStatus(ids, (byte) 1);
			break;
		case 2:
			this.updateItemStatus(ids, (byte) 2);
			break;
		case 3:
			this.updateItemStatus(ids, (byte) 3);
			break;
		default:
			System.out.println("不支持的商品状态");
			break;
		}

	}

	private void updateItemStatus(Long[] ids, byte status) {
		for (Long itemId : ids) {
			TbItem record = new TbItem();
			record.setId(itemId);
			record.setStatus(status);
			itemMapper.updateByPrimaryKeySelective(record);
		}
	}

	/**
	 * 根据商品id查询商品信息
	 * 
	 * @param id商品id
	 * @return
	 */
	@Override
	public ItemVo findById(Long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		ItemVo vo = new ItemVo();
		BeanUtils.copyProperties(item, vo);
		return vo;
	}

	/**
	 * 删除商品对应的信息,包括商品的基本信息,描述信息以及商品详细的参数
	 * @param ids-商品id对应的数组
	 * @return
	 */
	@Override
	public EgoResult deleteItems(Long[] ids) {
		if (null == ids || ids.length == 0)
			return null;
		for (Long id : ids) {
			itemMapper.deleteByPrimaryKey(id);
			itemDescMapper.deleteByPrimaryKey(id);
			TbItemParamItemExample example = new TbItemParamItemExample();
			Criteria criteria = example.createCriteria();
			criteria.andItemIdEqualTo(id);
			itemParamItemMapper.deleteByExample(example);
		}
		return EgoResult.ok();
	}

	/**
	 * 添加商品
	 * @param itemVo 商品的基本信息
	 * @param desc 商品的描述信息
	 * @param paramData 商品的规格参数
	 * @return
	 */
	@Override
	public EgoResult addItem(ItemVo itemVo, String desc, String paramData) {
		//生成添加时间
		Date date = new Date();
		// 生成商品id
		long itemId = IDUtils.getItemId();
		// 添加商品基本信息
		TbItem item = new TbItem();
		BeanUtils.copyProperties(itemVo, item);
		item.setCreated(date);
		item.setUpdated(date);
		item.setId(itemId);
		item.setStatus((byte) 1);
		itemMapper.insert(item);
		// 添加详细描述
		TbItemDesc itemDesc = new TbItemDesc(itemId, date, date, desc);
		itemDescMapper.insert(itemDesc);
		// 添加商品参数信息
		TbItemParamItem itemParamItem = new TbItemParamItem(itemId, date, date, paramData);
		itemParamItemMapper.insert(itemParamItem);
		return EgoResult.ok();
	}

	/**
	 * 修改商品
	 * @param itemVo 商品的基本信息
	 * @param desc 商品的描述信息
	 * @param paramData 商品的规格参数
	 * @return
	 */
	@Override
	public EgoResult updateItem(ItemVo itemVo, String desc, String paramData) {
		//生成修改时间
		Date date = new Date();
		// 添加商品基本信息
		TbItem item = new TbItem();
		BeanUtils.copyProperties(itemVo, item);
		long itemId = item.getId();
		Date created = item.getCreated();
		item.setUpdated(date);
		itemMapper.updateByPrimaryKey(item);
		// 添加详细描述
		TbItemDesc itemDesc = new TbItemDesc(itemId, item.getCreated(), date, desc);
		itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
		// 添加商品参数信息
		TbItemParamItem itemParamItem = new TbItemParamItem(itemId, created, date, paramData);
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		itemParamItemMapper.updateByExampleSelective(itemParamItem, example);
		return EgoResult.ok();
	}
}
