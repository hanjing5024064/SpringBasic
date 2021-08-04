package cn.mldn.mldnspring.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;
@Component
public class ConsumerMessageListener implements MessageListener {
	@Override
	public void onMessage(Message message) {				// 接收消息
		if (message instanceof TextMessage) {				// 判断发送的是否为文本信息
			TextMessage textMsg = (TextMessage) message ;	// 接收发送的消息信息
			try {
				System.out.println("*** 【接收到新消息】" + textMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
