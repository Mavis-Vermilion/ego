package cn.ego.rpc.pojo;

import java.util.List;

/**
 * 封装商城首页导航栏的数据模型的结果集
 * @author 起灬風了
 *
 */
public class ItemCatResult {

	private List<ItemCatNode> data;

	public List<ItemCatNode> getData() {
		return data;
	}

	public void setData(List<ItemCatNode> data) {
		this.data = data;
	}

}
