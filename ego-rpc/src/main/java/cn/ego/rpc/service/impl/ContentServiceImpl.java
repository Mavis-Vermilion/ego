package cn.ego.rpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.ego.mapper.TbContentMapper;
import cn.ego.pojo.TbContent;
import cn.ego.pojo.TbContentExample;
import cn.ego.pojo.TbContentExample.Criteria;
import cn.ego.rpc.dao.JedisDao;
import cn.ego.rpc.service.ContentService;
import cn.ego.utils.EgoResult;
import cn.ego.vo.ContentVo;

/**
 * 首页内容访问操作业务实现类
 * 
 * @author 起灬風了
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private JedisDao jedisDao;
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${INDEX_CONTENT}")
	private String INDEX_CONTENT;
	
	/**
	 * 根据categoryid查询出对应广告位数据content
	 * 
	 * @param categoryid
	 * @return
	 */
	@Override
	public String findByCategoryId(Long categoryid) {
		
		//请求缓存数据库,如果没有数据则请求MySQL
		try {
			String jsonString = jedisDao.hget(INDEX_CONTENT, String.valueOf(categoryid));
			if(jsonString != null && jsonString!= "")
				return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//请求MySQL
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		ContentVo vo = new ContentVo();
		List<ContentVo> volist = new ArrayList<>();
		if (list != null) {
			for (TbContent tbContent : list) {
				ContentVo clone = (ContentVo) vo.clone();
				BeanUtils.copyProperties(tbContent, clone);
				volist.add(clone);
			}
		}
		String jsonString = JSON.toJSONString(volist);
		//将mysql查询到的数据放入缓存数据库中
        try {
        	Long val = jedisDao.hset(INDEX_CONTENT, String.valueOf(categoryid), jsonString);
        	System.out.println(val!=0?"添加成功":"添加失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	@Override
	public EgoResult clearCache(Long categoryid) {
		Long val = jedisDao.hdel(INDEX_CONTENT, String.valueOf(categoryid));
		if(val!= null && val>0) {
			return EgoResult.ok();
		}
		return new EgoResult(400, "删除失败", "失败");
	}

}
