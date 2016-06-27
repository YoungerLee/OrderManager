package com.order.domain;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable {
	private String id;
	private String name;
	private String imgurl;
	private Double price;
	private Integer pnum;
	private Date startTime;
	private Date endTime;

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgurl() {
		return imgurl;
	}

	public String getImgurls() {
		return imgurl.substring(0, imgurl.lastIndexOf(".")) + "_s"
				+ imgurl.substring(imgurl.lastIndexOf("."));
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
