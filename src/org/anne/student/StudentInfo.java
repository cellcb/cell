package org.anne.student;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StudentInfo entity.
 */
//@Entity
@Table(name = "student_info")
public class StudentInfo implements java.io.Serializable {

	private static final long serialVersionUID = 3915564495160615440L;
	
	private String id;
	private String lastname;
	private String firstname;
	private Integer sex;
	private String address;
	private String city;
	private String nation;
	private String signature;
	private String tag;
	
	private String elementarySch;
	private String juniorSch;
	private String highSch;
	private String university;
	private String major;
	private String eduBg;
	
	private String wantNation;
	private String wantCity;
	private String wantUniversity;
	private String wantMajor;
	private Boolean wantScholarship;
	private Integer wantFee;
	
	private String userid;

	public StudentInfo() {
	}

	public StudentInfo(String lastname, String firstname, Integer sex,
			String address, String city, String nation, String signature,
			String tag, String elementarySch, String juniorSch, String highSch,
			String university, String major, String eduBg, String wantNation,
			String wantCity, String wantUniversity, String wantMajor,
			Boolean wantScholarship, Integer wantFee, String userid) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.sex = sex;
		this.address = address;
		this.city = city;
		this.nation = nation;
		this.signature = signature;
		this.tag = tag;
		this.elementarySch = elementarySch;
		this.juniorSch = juniorSch;
		this.highSch = highSch;
		this.university = university;
		this.major = major;
		this.eduBg = eduBg;
		this.wantNation = wantNation;
		this.wantCity = wantCity;
		this.wantUniversity = wantUniversity;
		this.wantMajor = wantMajor;
		this.wantScholarship = wantScholarship;
		this.wantFee = wantFee;
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getElementarySch() {
		return elementarySch;
	}

	public void setElementarySch(String elementarySch) {
		this.elementarySch = elementarySch;
	}

	public String getJuniorSch() {
		return juniorSch;
	}

	public void setJuniorSch(String juniorSch) {
		this.juniorSch = juniorSch;
	}

	public String getHighSch() {
		return highSch;
	}

	public void setHighSch(String highSch) {
		this.highSch = highSch;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEduBg() {
		return eduBg;
	}

	public void setEduBg(String eduBg) {
		this.eduBg = eduBg;
	}

	public String getWantNation() {
		return wantNation;
	}

	public void setWantNation(String wantNation) {
		this.wantNation = wantNation;
	}

	public String getWantCity() {
		return wantCity;
	}

	public void setWantCity(String wantCity) {
		this.wantCity = wantCity;
	}

	public String getWantUniversity() {
		return wantUniversity;
	}

	public void setWantUniversity(String wantUniversity) {
		this.wantUniversity = wantUniversity;
	}

	public String getWantMajor() {
		return wantMajor;
	}

	public void setWantMajor(String wantMajor) {
		this.wantMajor = wantMajor;
	}

	public Boolean getWantScholarship() {
		return wantScholarship;
	}

	public void setWantScholarship(Boolean wantScholarship) {
		this.wantScholarship = wantScholarship;
	}

	public Integer getWantFee() {
		return wantFee;
	}

	public void setWantFee(Integer wantFee) {
		this.wantFee = wantFee;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}