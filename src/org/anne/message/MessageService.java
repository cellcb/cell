package org.anne.message;

import java.util.List;

import org.anne.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

	@Autowired
	MessageDAO messageDAO;

	public List<Message> getNewPrivateMessageByUserId(MessageVO mv) {
		return messageDAO.getNewPrivateMessageByUserId(mv);
	}

	public int getNewPrivateMessageCountByUserId(String userid) {
		return messageDAO.getNewPrivateMessageCountByUserId(userid);
	}

	public List<Message> getPrivateMessageRecoed(MessageVO mv) {
		return messageDAO.getPrivateMessageRecoed(mv);
	}

	public int getPrivateMessageRecoedCount(MessageVO mv) {
		return messageDAO.getPrivateMessageRecoedCount(mv);
	}

	@Transactional
	public void insertMessage(Message entity) {
		messageDAO.insertMessage(entity);
	}

	@Transactional
	public void deleteMessageByID(String id) {
		messageDAO.deleteMessageByID(id);
	}

	@Transactional
	public void updateMessageStatus(String messageid) {
		messageDAO.updateMessageStatus(messageid);
	}
	
	public List<Message> getLeaveMessage(MessageVO mv){
		return messageDAO.getLeaveMessage(mv);
	}
}
