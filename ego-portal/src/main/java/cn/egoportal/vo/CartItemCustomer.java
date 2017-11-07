package cn.egoportal.vo;

import org.apache.commons.lang3.StringUtils;

import cn.ego.portal.pojo.CartItem;

public class CartItemCustomer extends CartItem{

	public String[] getImages() {
		String imgStr = this.getImage();
		if(StringUtils.isNotBlank(imgStr)) {
			return imgStr.split(",");
		}
		return null;
	}
}
