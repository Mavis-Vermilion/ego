package cn.ego.rpc.service;

import cn.ego.utils.EgoResult;

/**
 * 首页内容访问操作业务接口
 * @author 起灬風了
 *
 */
public interface ContentService {

	/**
	 * 根据categoryid查询出对应广告位数据content
	 * @param categoryid
	 * @return
	 */
	String findByCategoryId(Long categoryid);

	/**
	 * 清除首页缓存数据
	 * @param categoryid-模块id
	 * @return
	 */
	EgoResult clearCache(Long categoryid);
}
