package cn.mldn.mldnspring.service.impl;

import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.service.IMessageService;
@Service
public class MessageServiceImpl implements IMessageService {
	@Override
	public String echo(String str) {
		if (str == null) {
			throw new RuntimeException("空消息，无法处理！") ;
		}
		System.out.println("【MessageServiceImpl业务层实现】接收到消息内容：" + str);
		return "【ECHO】msg = " + str;
	}
}
