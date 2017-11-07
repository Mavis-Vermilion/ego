package cn.ego.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.portal.pojo.PageBean;
import cn.ego.portal.service.SearchService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.HttpClientUtil;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_URL}")
	private String SEARCH_URL;

	@Override
	public EgoResult search(String query, Integer page) {
		Map<String, String> param = new HashMap<>();
		param.put("query", query);
		param.put("page", page.toString());
		String json = HttpClientUtil.doGet(SEARCH_URL, param);
		System.out.println(json);
		EgoResult egoResult = EgoResult.formatToPojo(json, PageBean.class);
		return egoResult;
	}

}
