package cn.ego.pojo;
/**
 * EasyUI的树模型
 * @author 起灬風了
 *
 */
public class EasyUITreeModel{

	private Long id;
	private String text;
	private String state;

	public EasyUITreeModel() {
	}

	
	public EasyUITreeModel(Long id, String text, Boolean isParent) {
		this.id = id;
		this.text = text;
		if(isParent) {
			this.state="closed";
		}else {
			this.state="open";
		}
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
