<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String message_input_url = basePath + "pages/message/echo.action" ;
%>
<base href="<%=basePath%>" />
<h1>【警告】相同的帐号已经在其它设备上登录了，当前帐号强制下线！</h1>