package cn.mldn.mldnspring.vo;

import java.util.Date;

public class Item {
	private String title ;
	private Date createDate ;
	// setter、getter、toString()略
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Item [title=" + title + ", createDate=" + createDate + "]";
	}
	
}
