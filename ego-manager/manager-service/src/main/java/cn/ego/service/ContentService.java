package cn.ego.service;

import cn.ego.pojo.DatagridModel;
import cn.ego.pojo.PageBean;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ContentVo;

/**
 * 对首页内容进行管理的业务接口
 * @author 起灬風了
 *
 */
public interface ContentService {

	/**
	 * 查询出首页模块对应的内容
	 * @param categoryId
	 * @param page
	 * @return
	 */
	public DatagridModel<ContentVo> findBycategoryId(Long categoryId,PageBean page);

	/**
	 * 对首页模块的内容进行添加
	 * @param contentVo
	 * @return
	 */
	public EgoResult addContent(ContentVo contentVo);

	/**
	 * 修改首页模块的内容信息
	 * @param contentVo
	 * @return
	 */
	public EgoResult updateContent(ContentVo contentVo);

	/**
	 * 删除首页模块的内容信息
	 * @param ids
	 * @return
	 */
	public EgoResult deleteContents(Long[] ids);
}
