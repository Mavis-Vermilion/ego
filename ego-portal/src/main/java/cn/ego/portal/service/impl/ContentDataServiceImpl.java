package cn.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.ego.portal.pojo.ContentData;
import cn.ego.portal.service.ContentDataService;
import cn.ego.utils.HttpClientUtil;
import cn.ego.vo.ContentVo;

/**
 * 访问首页内容数据服务实现类
 * 
 * @author 起灬風了
 *
 */
@Service
public class ContentDataServiceImpl implements ContentDataService {

	@Value("${DATA_LOCATION}")
	private String DATA_LOCATION;// 数据服务中心地址加端口 如http://192.168.2.108:8081
	@Value("${DATA_ADDRESS}")
	private String DATA_ADDRESS;// 访问大广告位的地址
	@Value("${DATA_CATEGORYID}")
	private Integer DATA_CATEGORYID; // 大广告位的ID
	@Value("${IMG_HEIGHT}")
	private Integer IMG_HEIGHT;// 图片高度
	@Value("${IMG_WIDTH}")
	private Integer IMG_WIDTH;// 图片宽度
	@Value("${IMG_HEIGHT_B}")
	private Integer IMG_HEIGHT_B;// 图片B高度
	@Value("${IMG_WIDTH_B}")
	private Integer IMG_WIDTH_B;// 图片B宽度

	/**
	 * 大广告位的数据信息
	 * @return
	 */
	@Override
	public List<ContentData> findContentData() {
		String contentsStr = HttpClientUtil.doGet(DATA_LOCATION + DATA_ADDRESS + DATA_CATEGORYID);
		List<ContentVo> list = JSON.parseArray(contentsStr, ContentVo.class);
		List<ContentData> datas = new ArrayList<>();
		if (list != null) {
			for (ContentVo contentVo : list) {
				ContentData d = new ContentData();
				d.setAlt(contentVo.getTitle());
				d.setHref(contentVo.getUrl());
				d.setWidth(IMG_WIDTH);
				d.setHeight(IMG_HEIGHT);
				d.setSrc(contentVo.getPic());
				d.setHeightB(IMG_HEIGHT_B);
				d.setWidthB(IMG_WIDTH_B);
				d.setSrcB(contentVo.getPic2());
				datas.add(d);
			}
		}
		return datas;
	}

}
