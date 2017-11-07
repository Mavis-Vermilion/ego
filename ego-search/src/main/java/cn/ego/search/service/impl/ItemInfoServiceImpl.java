package cn.ego.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ego.search.dao.ItemInfoDao;
import cn.ego.search.mapper.ItemInfoMapper;
import cn.ego.search.service.ItemInfoService;
import cn.ego.search.vo.ItemInfoVo;
import cn.ego.search.vo.PageBean;
import cn.ego.utils.EgoResult;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {

	@Autowired
	private ItemInfoMapper itemInfoMapper;
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private ItemInfoDao itemInfoDao;
	@Override
	public EgoResult importItemInfo() {
		List<ItemInfoVo> list = itemInfoMapper.importItemInfo();
		if (list != null && list.size() > 0) {
			try {
				List<SolrInputDocument> docs = new ArrayList<>(list.size());
				for (ItemInfoVo itemInfoVo : list) {
					SolrInputDocument doc = new SolrInputDocument();
					doc.setField("id", itemInfoVo.getId());
					doc.setField("item_title", itemInfoVo.getTitle());
					doc.setField("item_sell_point", itemInfoVo.getSellPoint());
					doc.setField("item_price", itemInfoVo.getPrice());
					doc.setField("item_image", itemInfoVo.getImage());
					doc.setField("item_category_name", itemInfoVo.getName());
					doc.setField("item_desc", itemInfoVo.getItemDesc());
					docs.add(doc);
				}
				solrServer.add(docs);
				solrServer.commit();
				return EgoResult.ok();
			} catch (Exception e) {
				try {
					solrServer.rollback();
					return new EgoResult(400, "error", "添加失败");
				} catch (SolrServerException | IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();

			}

		}
		return null;

	}

	@Override
	public EgoResult searchIteminfo(PageBean page, String query) {
		List<ItemInfoVo> list = itemInfoDao.searchItemInfo(page, query);
		page.setData(list);
		return EgoResult.ok(page);
	}

}
