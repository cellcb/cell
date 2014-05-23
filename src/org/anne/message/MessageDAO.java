package org.anne.message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.anne.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {

	private final static String INSERT_MESSAGE = "insert into  message (id,sender,receiver,content,sentdate,status,type) values (?,?,?,?,?,?,?)";
	private final static String DELETE_MESSAGE_BY_ID = "delete  from  Message  where id=? ";
	private final static String SELECT_NEW_PRIVATEMESSAGE_BY_USERID = "SELECT * FROM MESSAGE WHERE RECEIVER=? AND TYPE=0   ORDER BY sentdate DESC LIMIT ?,?";
	private final static String SELECT_NEW_PRIVATEMESSAGE_COUNT_BY_USERID = "SELECT count(*) FROM MESSAGE WHERE RECEIVER=? AND TYPE=0  ";
	private final static String SELECT_PRIVATE_MESSAGE_RECORD = "SELECT  * FROM message WHERE sender=? AND receiver=? OR  sender=? AND receiver=?  ORDER BY sentdate DESC LIMIT ?,?";
	private final static String SELECT_PRIVATE_MESSAGE_RECORD_COUNT = "SELECT  COUNT(*) FROM message WHERE sender=? AND receiver=? OR  sender=? AND receiver=?  ";
	private final static String UPDATE_MESSAGE_STATUS = "update message set  status=1  where   ID=?";
	private final static String SELECT_LEAVEMESSAGE = "select * from Message where receiver=? and type=2 order by sentdate desc limit ?,?";
	private final static String SELECT_LEAVEMESSAGE_COUNT = "select count(*) from message where receiver=? and type=2";
	
	@Autowired
	JdbcTemplate jTemplate;

	public List<Message> getNewPrivateMessageByUserId(MessageVO mv) {
		return jTemplate.query(
				SELECT_NEW_PRIVATEMESSAGE_BY_USERID,
				new Object[] { mv.getReceiver(), mv.getStart(),
						mv.getPagesize() }, new MessageRowMapper());
	}

	public int getNewPrivateMessageCountByUserId(String userid) {
		return jTemplate.queryForInt(SELECT_NEW_PRIVATEMESSAGE_COUNT_BY_USERID,
				new Object[] { userid });
	}

	public List<Message> getPrivateMessageRecoed(MessageVO mv) {
		return jTemplate.query(
				SELECT_PRIVATE_MESSAGE_RECORD,
				new Object[] { mv.getSender(), mv.getReceiver(),
						mv.getReceiver(), mv.getSender(), mv.getStart(),
						mv.getPagesize() }, new MessageRowMapper());
	}

	public int getPrivateMessageRecoedCount(MessageVO mv) {
		return jTemplate.queryForInt(
				SELECT_PRIVATE_MESSAGE_RECORD_COUNT,
				new Object[] { mv.getSender(), mv.getReceiver(),
						mv.getReceiver(), mv.getSender()});
	}

	public void insertMessage(Message entity) {
		jTemplate.update(INSERT_MESSAGE, new Object[] { entity.getId(), entity.getSender(),
				entity.getReceiver(), entity.getContent(),
				entity.getSentdate(), entity.getStatus(), entity.getType()});
	}

	public void deleteMessageByID(String id) {
		jTemplate.update(DELETE_MESSAGE_BY_ID, new Object[] { id });
	}

	public void updateMessageStatus(String messageid) {
		jTemplate.update(UPDATE_MESSAGE_STATUS, new Object[] { messageid });
	}
	
	public List<Message> getLeaveMessage(MessageVO mv){
		return jTemplate.query(SELECT_LEAVEMESSAGE, new Object[]{
				mv.getReceiver(),mv.getStart(),mv.getPagesize()
		}, new MessageRowMapper());
	}

	class MessageRowMapper implements RowMapper<Message> {
		public Message mapRow(ResultSet rs, int index) throws SQLException {
			Message entity = new Message();
			entity.setId(rs.getString("id"));
			entity.setSender(rs.getString("sender"));
			entity.setReceiver(rs.getString("receiver"));
			entity.setContent(rs.getString("content"));
			entity.setStatus(rs.getInt("status"));
			entity.setType(rs.getInt("type"));
			entity.setSentdate(rs.getTimestamp("sentdate"));
			return entity;
		}
	}
}