package com.mendwe.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.mendwe.enumerations.Relationship;
import com.mendwe.enumerations.Religion;
import com.mendwe.enumerations.Sex;
import com.mendwe.enumerations.VegNonVeg;

@Entity

public class User {

	@Enumerated(EnumType.STRING)
	private VegNonVeg vegNonVeg;


	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	
	@Enumerated(EnumType.STRING)
	private Religion religion;

	
	@Enumerated(EnumType.STRING)
	private Relationship relation;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private String firstName,lastName;
	private int userId;
	private String username;
	private String password;
	private int mobilenum, age;
	private String  mailId, blogId;
	private String currentAddress, homeTown, organization, college, school;
	private String imagePath=null;
	
	
	private Date createdDate;
	
	public Date getCreatedDate() {
	 createdDate=new Date();
	return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	
	public Relationship getRelation() {
		return relation;
	}

	public void setRelation(Relationship relation) {
		this.relation = relation;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		System.out.println("userid setter is called ");
		this.userId = userId;
	}

	public int getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(int mobilenum) {
		this.mobilenum = mobilenum;
	}


	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public VegNonVeg getVegNonVeg() {
		return vegNonVeg;
	}

	public void setVegNonVeg(VegNonVeg vegNonVeg) {
		this.vegNonVeg = vegNonVeg;
	}

}



