package cn.ego.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.pojo.TbItemDesc;
import cn.ego.pojo.TbItemParamItem;
import cn.ego.portal.service.ItemService;
import cn.ego.utils.EgoResult;
import cn.ego.utils.HttpClientUtil;
import cn.ego.vo.ItemVo;
import cn.egoportal.vo.ItemCustomer;
@Service
public class ItemServiceImpl implements ItemService{

	@Value("${DATA_LOCATION}")
	private String DATA_LOCATION;
	@Value("${ITEM_INFO_ADDRESS}")
	private String ITEM_INFO_ADDRESS;
	@Value("${ITEM_DESC_ADDRESS}")
	private String ITEM_DESC_ADDRESS;
	@Value("${ITEM_PARAM_ADDRESS}")
	private String ITEM_PARAM_ADDRESS;
	@Override
	public ItemVo findItemByItemId(Long itemId) {
		String json = HttpClientUtil.doGet(DATA_LOCATION+ITEM_INFO_ADDRESS+"?itemId="+itemId);
		EgoResult egoResult = EgoResult.formatToPojo(json, ItemCustomer.class);
		return (ItemCustomer) egoResult.getData();
	}

	@Override
	public TbItemDesc findItemDescByItemId(Long itemId) {
		String json = HttpClientUtil.doGet(DATA_LOCATION+ITEM_DESC_ADDRESS+"?itemId="+itemId);
		EgoResult egoResult = EgoResult.formatToPojo(json, TbItemDesc.class);
		return (TbItemDesc) egoResult.getData();
	}

	
	@Override
	public String findItemParamByItemId(Long itemId) {
		String json = HttpClientUtil.doGet(DATA_LOCATION+ITEM_PARAM_ADDRESS+"?itemId="+itemId);
		EgoResult egoResult = EgoResult.formatToPojo(json, TbItemParamItem.class);
		TbItemParamItem param = (TbItemParamItem) egoResult.getData();
		String paramData = param.getParamData();
			
		return paramData;
	}
}
