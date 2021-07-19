package cn.mldn.mldnspring.service.impl;

import cn.mldn.mldnspring.service.IMessage;

public class MessageImpl implements IMessage {	// 建立接口实现子类
	@Override
	public String echo(String msg) {			// 覆写接口方法
		return "【ECHO】" + msg ; 
	}
}

