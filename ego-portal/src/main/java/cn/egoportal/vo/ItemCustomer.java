package cn.egoportal.vo;

import org.apache.commons.lang3.StringUtils;

import cn.ego.vo.ItemVo;

public class ItemCustomer extends ItemVo{

	public String[] getImages() {
		String imgStr = this.getImage();
		if(StringUtils.isNotBlank(imgStr)) {
			return imgStr.split(",");
		}
		return null;
	}
}
