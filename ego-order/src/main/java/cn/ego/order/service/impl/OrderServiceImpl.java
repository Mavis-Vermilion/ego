package cn.ego.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ego.mapper.TbOrderItemMapper;
import cn.ego.mapper.TbOrderMapper;
import cn.ego.mapper.TbOrderShippingMapper;
import cn.ego.order.dao.JedisDao;
import cn.ego.order.service.OrderService;
import cn.ego.pojo.TbOrder;
import cn.ego.pojo.TbOrderItem;
import cn.ego.pojo.TbOrderShipping;
import cn.ego.utils.DateUtils;
import cn.ego.utils.EgoResult;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private JedisDao jedisDao;
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Value("${ORDER_ID_KEY}")
	private String ORDER_ID_KEY;
	@Value("${ORDER_ID_INITIAL_VALUE}")
	private Long ORDER_ID_INITIAL_VALUE;
	
	@Override
	public EgoResult addOrder(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) throws Exception{
		
		
			String orderId = jedisDao.get(ORDER_ID_KEY);
			Long newOrderId = null;
			if(StringUtils.isBlank(orderId)) {
				jedisDao.set(ORDER_ID_KEY, ORDER_ID_INITIAL_VALUE+"");
				newOrderId = ORDER_ID_INITIAL_VALUE;
			}else
				newOrderId = jedisDao.incr(ORDER_ID_KEY);
			
			Date date = new Date();
			order.setOrderId(newOrderId+"");
			order.setCreateTime(date);
			order.setUpdateTime(date);
			order.setPaymentTime(date);
			order.setPaymentType(1);
			orderMapper.insert(order);
			for (TbOrderItem tbOrderItem : orderItems) {
				String orderItemId = DateUtils.getCurrentDateTime("yyyyMMddHHmmssSSS")+cn.ego.utils.StringUtils.getRandomStr(3);
				tbOrderItem.setOrderId(newOrderId+"");
				tbOrderItem.setId(orderItemId);
				orderItemMapper.insert(tbOrderItem);
			}
			orderShipping.setOrderId(newOrderId+"");
			orderShipping.setCreated(date);
			orderShipping.setUpdated(date);
			orderShippingMapper.insert(orderShipping);
			return EgoResult.ok(newOrderId);
		
	}

}
