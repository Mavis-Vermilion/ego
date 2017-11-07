package cn.ego.search.service;

import cn.ego.search.vo.PageBean;
import cn.ego.utils.EgoResult;

public interface ItemInfoService {

	/**
	 * 将数据库信息添加到solr的索引库
	 * 
	 * @return
	 */
	public EgoResult importItemInfo();

	/**
	 * 
	 * @param page-分页信息
	 * @param query-检索条件
	 * @return
	 */
	EgoResult searchIteminfo(PageBean page, String query);
}
