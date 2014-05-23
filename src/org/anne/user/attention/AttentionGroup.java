package org.anne.user.attention;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AttentionGroup entity.
 */
//@Entity
@Table(name = "attention_group", catalog = "anne")
public class AttentionGroup implements java.io.Serializable {

	private Integer id;
	private String groupname;
	private String userid;

	public AttentionGroup() {
	}

	public AttentionGroup(String groupname, String userid) {
		this.groupname = groupname;
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

	@Column(name = "groupname", length = 100)
	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "userid", length = 40)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}