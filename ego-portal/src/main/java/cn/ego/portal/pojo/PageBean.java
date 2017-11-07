package cn.ego.portal.pojo;

public class PageBean {
	private Integer currentPage;// 当前页
	private Integer pageSize = 10;// 每页条数
	private Long totalPage;// 总页数
	private Long totalSize;// 总条数
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
		this.totalPage = this.totalSize % this.pageSize == 0 ? this.totalSize / this.pageSize
				: this.totalSize / this.pageSize + 1;
	}

}
