package org.anne.user.attention;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Attention entity.
 */
//@Entity
@Table(name = "attention")
public class Attention implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2212406499152901101L;
	private Integer id;
	private String userid;
	private String targetUserid;
	private Integer groupid;

	public Attention() {
	}

	public Attention(String userid, String targetUserid, Integer groupid) {
		this.userid = userid;
		this.targetUserid = targetUserid;
		this.groupid = groupid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid", length = 40)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "target_userid", length = 40)
	public String getTargetUserid() {
		return this.targetUserid;
	}

	public void setTargetUserid(String targetUserid) {
		this.targetUserid = targetUserid;
	}

	@Column(name = "groupid")
	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

}