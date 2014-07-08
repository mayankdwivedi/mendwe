package com.mendwe.model;

import java.util.Date;

public class Posts {

	String text;
	String location;
	String thumbsUp;
	String thumbsDown;
	String comment;
    String postedBy;
    Date createdDate;
	public Date getCreatedDate() {
		return createdDate=new Date();
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(String thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	public String getThumbsDown() {
		return thumbsDown;
	}
	public void setThumbsDown(String thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}	
}
