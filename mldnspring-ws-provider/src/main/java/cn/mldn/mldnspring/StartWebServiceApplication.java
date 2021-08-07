package cn.mldn.mldnspring;

import javax.xml.ws.Endpoint;

import cn.mldn.mldnspring.service.IMessageService;
import cn.mldn.mldnspring.service.impl.MessageServiceImpl;

public class StartWebServiceApplication {
	public static void main(String[] args) {
		IMessageService msgObj = new MessageServiceImpl() ;	// 实例化接口对象
		Endpoint.publish("http://127.0.0.1:7778/message", msgObj) ;
	}
}
