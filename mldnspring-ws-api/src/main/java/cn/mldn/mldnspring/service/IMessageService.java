package cn.mldn.mldnspring.service;

import javax.jws.WebService;
@WebService					// 必须将此接口标注为WebService接口 
public interface IMessageService {
	/**
	 * 信息回应处理
	 * @param msg 要处理的消息
	 * @return 添加“ECHO”信息后返回
	 */
	public String echo(String msg);
}

