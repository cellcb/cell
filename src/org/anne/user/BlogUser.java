package org.anne.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BlogUser entity.
 */
// @Entity
@Table(name = "blog_user")
public class BlogUser implements java.io.Serializable {

	private static final long serialVersionUID = -5007445289655806790L;
	private String userid;
	private String username;
	private String nickname;
	private String passwd;
	private String email;
	private String icon;
	private String signature;
	private Integer status; // 0 正常 ，1 锁定
	private Integer type;
	private Integer attentionCount;
	private Integer fansCount;
	private Integer blogCount;
	private Integer deleteFlag;

	public BlogUser() {
	}

	public BlogUser(String userid) {
		this.userid = userid;
	}

	public BlogUser(String userid, String username, String nickname,
			String passwd, String email, String icon, String signature,
			Integer status, Integer type, Integer attentionCount,
			Integer fansCount, Integer blogCount) {
		this.userid = userid;
		this.username = username;
		this.nickname = nickname;
		this.passwd = passwd;
		this.email = email;
		this.icon = icon;
		this.signature = signature;
		this.status = status;
		this.type = type;
		this.attentionCount = attentionCount;
		this.fansCount = fansCount;
		this.blogCount = blogCount;
	}

	@Id
	@Column(name = "userid", unique = true, nullable = false, length = 40)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "nickname", length = 100)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "passwd", length = 100)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "icon", length = 200)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "Signature", length = 300)
	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "attention_count")
	public Integer getAttentionCount() {
		return this.attentionCount;
	}

	public void setAttentionCount(Integer attentionCount) {
		this.attentionCount = attentionCount;
	}

	@Column(name = "fans_count")
	public Integer getFansCount() {
		return this.fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	@Column(name = "blog_count")
	public Integer getBlogCount() {
		return this.blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}