package org.anne.message;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Message entity.
 */
// @Entity
@Table(name = "message")
public class Message implements java.io.Serializable {

	private static final long serialVersionUID = -5352142555675112394L;
	private String id;
	private String sender;
	private String receiver;
	private String content;
	private Date sentdate;
	private Integer status; // 0 新消息 1 已查看
	private Integer type; //消息类型 0 私信 1

	public Message() {
	}

	public Message(String sender, String receiver, String content,
			Timestamp sentdate, Integer status) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.sentdate = sentdate;
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "sender", length = 40)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "receiver", length = 40)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "content", length = 5000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "sentdate")
	public Date getSentdate() {
		return this.sentdate;
	}

	public void setSentdate(Timestamp sentdate) {
		this.sentdate = sentdate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}