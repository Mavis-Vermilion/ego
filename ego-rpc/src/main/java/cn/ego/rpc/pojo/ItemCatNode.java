package cn.ego.rpc.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商城首页导航栏的数据模型
 * @author 起灬風了
 *
 */
public class ItemCatNode {

	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List<?> itemCat;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getItemCat() {
		return itemCat;
	}

	public void setItemCat(List<?> itemCat) {
		this.itemCat = itemCat;
	}

}
