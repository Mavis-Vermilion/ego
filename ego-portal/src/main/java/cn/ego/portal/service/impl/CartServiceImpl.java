package cn.ego.portal.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.ego.portal.pojo.CartItem;
import cn.ego.portal.service.CartService;
import cn.ego.portal.utils.CookieUtils;
import cn.ego.utils.EgoResult;
import cn.ego.utils.HttpClientUtil;
import cn.ego.utils.JsonUtils;
import cn.ego.vo.ItemVo;
import cn.egoportal.vo.CartItemCustomer;
@Service
public class CartServiceImpl implements CartService{

	@Value("${DATA_LOCATION}")
	private String DATA_LOCATION;
	@Value("${ITEM_INFO_ADDRESS}")
	private String ITEM_INFO_ADDRESS;
	@Value("${COOKIE_CART_KEY}")
	private String COOKIE_CART_KEY;
	@Override
	public EgoResult addItem2Cart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		String egoResultStr = HttpClientUtil.doGet(DATA_LOCATION+ITEM_INFO_ADDRESS+"?itemId="+itemId);
		if(StringUtils.isBlank(egoResultStr))
			return EgoResult.build(400, "no found item");
		EgoResult egoResult = EgoResult.formatToPojo(egoResultStr, ItemVo.class);
		ItemVo item = (ItemVo) egoResult.getData();
		List<CartItem> list = this.getCart(request);
		CartItem temp = null;
		if(!CollectionUtils.isEmpty(list)) {
			for (CartItem cartItem : list) {
				if(item.getId().equals(cartItem.getId())) {
					cartItem.setNum(cartItem.getNum()+num);
					temp = cartItem;
					break;
				}
			}
		}
		
		if(temp==null) {
			temp = new CartItem();
			temp.setId(item.getId());
			temp.setNum(num);
			try {
				temp.setTitle(new String(item.getTitle().getBytes("utf-8")));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			temp.setPrice(item.getPrice());
			temp.setImage(item.getImage());
			list.add(temp);
		}
		String json = JsonUtils.objectToJson(list);
		//CookieUtils.deleteCookie(request, response, COOKIE_CART_KEY);
		CookieUtils.setCookie(request, response, COOKIE_CART_KEY, json);
		return EgoResult.ok();
	}

	
	private List<CartItem> getCart(HttpServletRequest request) {
		try {
			String cookieValue = CookieUtils.getCookieValue(request, COOKIE_CART_KEY);
			if(StringUtils.isBlank(cookieValue))
				return new ArrayList<>();
			List<CartItem> list = JsonUtils.jsonToList(cookieValue, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
	}


	@Override
	public List<CartItemCustomer> showCart(HttpServletRequest request) {
		try {
			String cookieValue = CookieUtils.getCookieValue(request, COOKIE_CART_KEY);
			if(StringUtils.isBlank(cookieValue))
				return new ArrayList<>();
			List<CartItemCustomer> list = JsonUtils.jsonToList(cookieValue, CartItemCustomer.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
