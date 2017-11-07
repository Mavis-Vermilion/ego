package cn.ego.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ego.mapper.TbContentMapper;
import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.pojo.TbContent;
import cn.ego.pojo.TbContentExample;
import cn.ego.pojo.TbContentExample.Criteria;
import cn.ego.service.ContentService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.HttpClientUtil;
import cn.ego.vo.ContentVo;

/**
 * 对首页内容进行管理的业务实现类
 * @author 起灬風了
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${DATA_SERVICE_CENTER_URL}")
	private String DATA_SERVICE_CENTER_URL;//数据服务中心的url
	@Value("${DATA_SERVICE_CENTER_LOCAL}")
	private String DATA_SERVICE_CENTER_LOCAL;//请求的地址
	@Autowired
	private TbContentMapper contentMapper;

	/**
	 * 查询出首页模块对应的内容
	 * @param categoryId
	 * @param page
	 * @return
	 */
	@Override
	public DatagridModel<ContentVo> findBycategoryId(Long categoryId, PageBean page) {
		PageHelper.startPage(page.getPage(), page.getRows());//分页参数
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);//分页条件查询
		PageInfo<TbContent> info = new PageInfo<>(list);
		long total = info.getTotal();// 总条数
		List<ContentVo> volist = new ArrayList<>();
		if (null != list && list.size() > 0) {
			ContentVo vo = new ContentVo();
			for (TbContent TbContent : list) {
				ContentVo clone = (ContentVo) vo.clone();
				BeanUtils.copyProperties(TbContent, clone);
				volist.add(clone);
			}
		}

		DatagridModel<ContentVo> model = new DatagridModel<>();
		model.setTotal(total);
		model.setRows(volist);
		return model;
	}

	/**
	 * 对首页模块的内容进行添加
	 * @param contentVo
	 * @return
	 */
	@Override
	public EgoResult addContent(ContentVo contentVo) {
		//请求数据服务中心,清除缓存
		HttpClientUtil.doGet(DATA_SERVICE_CENTER_URL+DATA_SERVICE_CENTER_LOCAL+contentVo.getCategoryId());
		Date date = new Date();
		TbContent content = new TbContent();
		BeanUtils.copyProperties(contentVo, content);
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		return EgoResult.ok();
	}

	/**
	 * 修改首页模块的内容信息
	 * @param contentVo
	 * @return
	 */
	@Override
	public EgoResult updateContent(ContentVo contentVo) {
		//请求数据服务中心,清除缓存
		HttpClientUtil.doGet(DATA_SERVICE_CENTER_URL+DATA_SERVICE_CENTER_LOCAL+contentVo.getCategoryId());
		Date date = new Date();
		TbContent content = new TbContent();
		BeanUtils.copyProperties(contentVo, content);
		content.setUpdated(date);
		contentMapper.updateByPrimaryKeyWithBLOBs(content);
		return EgoResult.ok();
	}

	/**
	 * 删除首页模块的内容信息
	 * @param ids
	 * @return
	 */
	@Override
	public EgoResult deleteContents(Long[] ids) {
		if (null == ids || ids.length == 0)
			return null;
		TbContent content = contentMapper.selectByPrimaryKey(ids[0]);
		//请求数据服务中心,清除缓存
		HttpClientUtil.doGet(DATA_SERVICE_CENTER_URL+DATA_SERVICE_CENTER_LOCAL+content.getCategoryId());
		for (Long id : ids) {
			contentMapper.deleteByPrimaryKey(id);
		}
		return EgoResult.ok();
	}

}
