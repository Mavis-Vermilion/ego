package cn.ego.portal.service;

import java.util.List;

import cn.ego.portal.pojo.ContentData;

/**
 * 访问首页内容数据服务接口
 * @author 起灬風了
 *
 */
public interface ContentDataService {

	/**
	 * 大广告位的数据信息
	 * @return
	 */
	public List<ContentData> findContentData();
}
