<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String message_input_url = basePath + "pages/message/echo.action" ;
%>
<base href="<%=basePath%>" />
<form action="<%=message_input_url%>" method="post">
	消息内容：	<input type="text" name="msg" id="msg" value="www.mldn.cn"><br>
	消息级别：	<select id="level" name="level">
				<option value="0">紧急</option>
				<option value="1">普通</option>
				<option value="2">延迟</option>
			</select><br>
	发送日期：	<input type="text" id="pubdate" name="pubdate" value="2262-01-21"><br>
	消息标签：	<input type="checkbox" name="tags" id="tags" value="政治">政治
			<input type="checkbox" name="tags" id="tags" value="经济">经济
			<input type="checkbox" name="tags" id="tags" value="文化">文化<br>
	<input type="submit" value="发送">	<input type="reset" value="重置">
</form> 
