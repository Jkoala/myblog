package com.simple.po;

import java.util.List;

public class Tree {
	
	
	private int id ; 
	private String text ; 
	private List<Tree> children;
	private Tree attributes;//其实也是note
	private boolean checked ; 
	private String state ;//open就是代码到了叶子节点。  close就是支干  下面有可能有节点 
	private String url;
	private String iconCls;
	private String permissions;
	
	
	
	
	
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public Tree getAttributes() {
		return attributes;
	}
	public void setAttributes(Tree attributes) {
		this.attributes = attributes;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	

	
	
	
	
	
	
}
