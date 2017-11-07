package cn.ego.pojo;

/**
 * 封装分页数据的实体bean
 * @author 起灬風了
 *
 */
public class PageBean {

	private int total;// 总条数
	private int rows;// 每页多少条
	private int page;// 当前页
	private int totalPage;// 总页数

	public PageBean() {
	}

	public PageBean(int rows, int page) {
		super();
		this.rows = rows;
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		this.totalPage = this.total % this.rows == 0 ? this.total / this.rows : (this.total % this.rows) + 1;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
