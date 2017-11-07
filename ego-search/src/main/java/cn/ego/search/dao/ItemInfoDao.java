package cn.ego.search.dao;

import java.util.List;

import cn.ego.search.vo.ItemInfoVo;
import cn.ego.search.vo.PageBean;
/**
 * 
 * @author 起灬風了
 *
 */
public interface ItemInfoDao {

	/**
	 * solr检索商品信息
	 * @param query检索配置
	 * @return
	 */
	List<ItemInfoVo> searchItemInfo(PageBean page, String query);
}
