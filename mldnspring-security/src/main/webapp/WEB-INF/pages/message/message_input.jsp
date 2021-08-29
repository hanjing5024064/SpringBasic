<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String message_input_url = basePath + "pages/message/show.action" ;
%>
<base href="<%=basePath%>" />
<form action="<%=message_input_url%>" method="post">
	消息内容：	<input type="text" name="msg" id="msg" value="www.mldn.cn"><br>
	<!-- 传递csrf-token信息，参数名称为“_csrf”，参数内容为随机生成的token数据 -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type="submit" value="发送">	<input type="reset" value="重置">
</form> 
