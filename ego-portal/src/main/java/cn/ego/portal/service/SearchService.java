package cn.ego.portal.service;
/**
 * 首页搜索操作接口
 * @author 起灬風了
 *
 */

import cn.ego.utils.EgoResult;

public interface SearchService {

	EgoResult search(String query,Integer page);
}
