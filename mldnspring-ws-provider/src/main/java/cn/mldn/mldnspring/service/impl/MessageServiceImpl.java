package cn.mldn.mldnspring.service.impl;

import javax.jws.WebService;

import cn.mldn.mldnspring.service.IMessageService;
@WebService(								// 定义Web服务接口
		endpointInterface="cn.mldn.mldnspring.service.IMessageService",	// 定义终端业务接口
		serviceName="messageService")		// 定义服务名称
public class MessageServiceImpl implements IMessageService {
	@Override
	public String echo(String str) {
		if (str == null) {
			throw new RuntimeException("空消息，无法处理！") ;
		}
		return "【ECHO】msg = " + str;
	}
}
