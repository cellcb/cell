package org.anne.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageAccessory entity.
 */
//@Entity
@Table(name = "message_accessory")
public class MessageAccessory implements java.io.Serializable {

	private static final long serialVersionUID = 9115261688570715222L;
	private Integer id;
	private String fileid;
	private String filename;
	private Integer type;
	private Integer messageid;

	public MessageAccessory() {
	}

	public MessageAccessory(String fileid, String filename, Integer type,
			Integer messageid) {
		this.fileid = fileid;
		this.filename = filename;
		this.type = type;
		this.messageid = messageid;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "fileid", length = 200)
	public String getFileid() {
		return this.fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	@Column(name = "filename", length = 500)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "messageid")
	public Integer getMessageid() {
		return this.messageid;
	}

	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}

}