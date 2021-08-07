package cn.mldn.mldnspring.client;

import cn.mldn.mldnspring.service.IMessageService;
import cn.mldn.mldnspring.service.impl.MessageServiceLocator;

public class TestMessageClient {
	public static void main(String[] args) throws Exception {
		IMessageService messageService = new MessageServiceLocator().getMessageServiceImplPort();
		System.out.println(messageService.echo("www.mldn.cn"));
	}
}
