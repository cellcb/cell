package org.anne.Organization;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrganizationInfo entity.
 */
@Entity
@Table(name = "organization_info", catalog = "anne")
public class OrganizationInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3397276012383589216L;
	
	private String id;
	private String fullname;
	private String nickname;
	private String chinesename;
	private Boolean publicschool;
	//成立日期
	private String registerdate;
	private String address;
	private String postcode;
	private String brief;
	private String officialwebsite;
	private String majorBrief;
	private Integer toefl;
	private Integer ielts;
	private Integer gre;
	private Integer sat;
	private String tag;
	private Integer undergraduate;
	private Integer postgraduate;
	private Integer fee;
	private String scholarship;
	private String email;
	private String userid;

	public OrganizationInfo() {
	}

	public OrganizationInfo(String fullname, String nickname,
			String chinesename, Boolean publicschool, String registerdate,
			String address, String postcode, String brief,
			String officialwebsite, String majorBrief, Integer toefl,
			Integer ielts, Integer gre, Integer sat, String tag, Integer undergraduate,
			Integer postgraduate, Integer fee, String scholarship, String email,
			String userid) {
		super();
		this.fullname = fullname;
		this.nickname = nickname;
		this.chinesename = chinesename;
		this.publicschool = publicschool;
		this.registerdate = registerdate;
		this.address = address;
		this.postcode = postcode;
		this.brief = brief;
		this.officialwebsite = officialwebsite;
		this.majorBrief = majorBrief;
		this.toefl = toefl;
		this.ielts = ielts;
		this.gre = gre;
		this.sat = sat;
		this.tag = tag;
		this.undergraduate = undergraduate;
		this.postgraduate = postgraduate;
		this.fee = fee;
		this.scholarship = scholarship;
		this.email = email;
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getChinesename() {
		return chinesename;
	}

	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}

	public Boolean isPublicschool() {
		return publicschool;
	}

	public void setPublicschool(Boolean publicschool) {
		this.publicschool = publicschool;
	}

	public String getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getOfficialwebsite() {
		return officialwebsite;
	}

	public void setOfficialwebsite(String officialwebsite) {
		this.officialwebsite = officialwebsite;
	}

	public String getMajorBrief() {
		return majorBrief;
	}

	public void setMajorBrief(String majorBrief) {
		this.majorBrief = majorBrief;
	}

	public Integer getToefl() {
		return toefl;
	}

	public void setToefl(Integer toefl) {
		this.toefl = toefl;
	}

	public Integer getIelts() {
		return ielts;
	}

	public void setIelts(Integer ielts) {
		this.ielts = ielts;
	}

	public Integer getGre() {
		return gre;
	}

	public void setGre(Integer gre) {
		this.gre = gre;
	}

	public Integer getSat() {
		return sat;
	}

	public void setSat(Integer sat) {
		this.sat = sat;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getUndergraduate() {
		return undergraduate;
	}

	public void setUndergraduate(Integer undergraduate) {
		this.undergraduate = undergraduate;
	}

	public Integer getPostgraduate() {
		return postgraduate;
	}

	public void setPostgraduate(Integer postgraduate) {
		this.postgraduate = postgraduate;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getScholarship() {
		return scholarship;
	}

	public void setScholarship(String scholarship) {
		this.scholarship = scholarship;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}