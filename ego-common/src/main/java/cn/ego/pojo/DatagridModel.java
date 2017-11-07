package cn.ego.pojo;

import java.util.List;

/**
 * Easyui的Datagrid模型
 * @author 起灬風了
 *
 * @param <T> 保存的实体
 */
public class DatagridModel<T> {

	private long total;
	private List<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
