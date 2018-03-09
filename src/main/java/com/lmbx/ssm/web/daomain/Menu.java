package com.lmbx.ssm.web.daomain;

import java.beans.Transient;

public class Menu {

	private String id;
	private String text;
	private String type;
	private String title;
	private String permission;
	private String target;
	private String parent;
	private Integer menuOrder;
	
	private Integer menurn;
	
	public Menu(){
		super();
	}
	
	public Menu(String id, String text, String type, String title, String permission, 
			String target, String parent, Integer menuOrder){
		this.id = id;
		this.text = text;
		this.type = type;
		this.title = title;
		this.permission = permission;
		this.target = target;
		this.parent = parent;
		this.menuOrder = menuOrder;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	@Transient
	public Integer getMenurn() {
		return menurn;
	}

	public void setMenurn(Integer menurn) {
		this.menurn = menurn;
	}

	public String getParent() {
		return parent;
	}

	
}
