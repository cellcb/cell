package org.anne.user.blacklist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Blacklist entity. @author MyEclipse Persistence Tools
 */
//@Entity
@Table(name = "blacklist", catalog = "anne")
public class Blacklist implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5348311260342641771L;
	private Integer id;
	private String blacklistUserid;
	private String userid;


	public Blacklist() {
	}

	public Blacklist(String blacklistUserid, String userid) {
		this.blacklistUserid = blacklistUserid;
		this.userid = userid;
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

	@Column(name = "blacklist_userid", length = 40)
	public String getBlacklistUserid() {
		return this.blacklistUserid;
	}

	public void setBlacklistUserid(String blacklistUserid) {
		this.blacklistUserid = blacklistUserid;
	}

	@Column(name = "userid", length = 40)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}