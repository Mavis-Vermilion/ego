package cn.ego.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ego.search.dao.ItemInfoDao;
import cn.ego.search.vo.ItemInfoVo;
import cn.ego.search.vo.PageBean;
@Repository
public class ItemInfoDaoImpl implements ItemInfoDao{

	@Autowired
	private SolrServer solrServer;
	@Override
	public List<ItemInfoVo> searchItemInfo(PageBean page, String query) {
		try {
			SolrQuery solrQuery = new SolrQuery(query);
			//检索范围
			solrQuery.setParam("df","item_keywords");
			//分页
			solrQuery.setStart((page.getCurrentPage()-1)*page.getPageSize());
			solrQuery.setRows(page.getPageSize());
			//设置Highlight
			solrQuery.setHighlight(true);
			solrQuery.addHighlightField("item_title");
			solrQuery.setHighlightSimplePre("<font color=\"red\">");
			solrQuery.setHighlightSimplePost("</font>");
			QueryResponse response = solrServer.query(solrQuery);
			SolrDocumentList documentList = response.getResults();
			//设置总条数
			page.setTotalSize(documentList.getNumFound());
			Map<String, Map<String, List<String>>> map = response.getHighlighting();
			List<ItemInfoVo> volist = new ArrayList<ItemInfoVo>();
			for (SolrDocument solrDocument : documentList) {
				ItemInfoVo vo = new ItemInfoVo();
				String id = (String)solrDocument.get("id");
				vo.setId(Long.parseLong(id));
				vo.setSellPoint((String) solrDocument.get("item_sell_point"));
				vo.setPrice((Long) solrDocument.get("item_price"));
				vo.setImage((String) solrDocument.get("item_image"));
				vo.setName((String) solrDocument.get("item_category_name"));
				vo.setTitle((String) solrDocument.get("item_title"));
				//获取Highlight结果
				List<String> list = map.get(id).get("item_title");
				if(list != null && !list.isEmpty()) {
					vo.setTitle(list.get(0));
				}else {
					vo.setTitle((String) solrDocument.get("item_title"));
				}
				volist.add(vo);
			}
			return volist;
		} catch (SolrServerException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
