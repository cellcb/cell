package org.anne.message;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageReply entity.
 */
//@Entity
@Table(name = "message_reply")
public class MessageReply implements java.io.Serializable {

	private static final long serialVersionUID = -2848181334400585643L;

	private Integer id;
	private String content;
	private Timestamp createdate;
	private String createuser;

	public MessageReply() {
	}

	public MessageReply(String content, Timestamp createdate, String createuser) {
		this.content = content;
		this.createdate = createdate;
		this.createuser = createuser;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "content", length = 4000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createdate", length = 19)
	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Column(name = "createuser", length = 40)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

}