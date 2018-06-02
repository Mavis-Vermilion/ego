package cn.ego.pojo;

import java.util.Date;

public class TbItemParam implements Cloneable {
	private Long id;

	private Long itemCatId;

	private Date created;

	private Date updated;

	private String paramData;

	
	
	public TbItemParam() {
	}

	public TbItemParam(Long itemCatId, Date created, Date updated, String paramData) {
		this.itemCatId = itemCatId;
		this.created = created;
		this.updated = updated;
		this.paramData = paramData == null ? null : paramData.trim();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemCatId() {
		return itemCatId;
	}

	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData == null ? null : paramData.trim();
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}