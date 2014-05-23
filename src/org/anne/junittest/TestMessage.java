package org.anne.junittest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.anne.base.AnneUtil;
import org.anne.blog.Blog;
import org.anne.blog.reply.BlogReply;
import org.anne.blog.transmit.TransmitRelation;
import org.anne.message.Message;
import org.anne.message.MessageService;
import org.anne.message.vo.MessageVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestMessage {

	SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	MessageService mess;

	@Test
	public void addMessageTest() {

		Message b = new Message();
		b.setId(AnneUtil.genId());
		b.setContent(" c");
		b.setReceiver("cb");
		b.setSender("zy");
		b.setSentdate(new Date());
		b.setStatus(0);
		b.setType(0);
		try {
			mess.insertMessage(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getNewPrivateMessageByUserId() {
		MessageVO mv = new MessageVO();
		mv.setReceiver("zy");
		mv.setStart(0);
		mv.setPagesize(100);
		List<Message> newPrivateMessageByUserId = mess
				.getNewPrivateMessageByUserId(mv);
		for (Iterator iterator = newPrivateMessageByUserId.iterator(); iterator
				.hasNext();) {
			Message message = (Message) iterator.next();
			System.out.println(message.getContent());
		}
	}

	@Test
	public void getNewPrivateMessageCountByUserId() {

		System.out.println(mess.getNewPrivateMessageCountByUserId("zy"));
	}

	@Test
	public void getPrivateMessageRecoed() {
		MessageVO mv = new MessageVO();
		mv.setReceiver("zy");
		mv.setSender("cb");
		mv.setStart(0);
		mv.setPagesize(100);
		List<Message> privateMessageRecoed = mess.getPrivateMessageRecoed(mv);
		for (Message message : privateMessageRecoed) {
			System.out.println(message.getContent() + " " + message.getSender()
					+ " " + message.getReceiver());
		}
	}

	@Test
	public void getPrivateMessageRecoedCount() {
		MessageVO mv = new MessageVO();
		mv.setReceiver("zy");
		mv.setSender("cb");
		mv.setStart(0);
		mv.setPagesize(100);
		System.out.println(mess.getPrivateMessageRecoedCount(mv));
	}

	@Test
	public void deleteMessageByID() {
		mess.deleteMessageByID("1345555395125e2b576c1-ad14-4d6b-ac5e-b60");
	}

	@Test
	public void updateMessageStatus() {
		mess.updateMessageStatus("1345555395125e2b576c1-ad14-4d6b-ac5e-b60");
	}
}
