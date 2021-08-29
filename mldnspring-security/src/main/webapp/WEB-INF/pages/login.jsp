<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String login_url = basePath + "mldn-login" ; // 登录表单提交路径，该路径需要单独配置
%>
<title>SpringSecurity安全框架</title>
<base href="<%=basePath%>" />
</head>
<body>
<h2>用户登录</h2>
<form action="<%=login_url%>" method="post">
	用户名：<input type="text" name="mid"><br>
	密&nbsp;&nbsp;&nbsp;码：<input type="password" name="pwd"><br>
	<input type="checkbox" id="remember" name="remember" value="true"/>下次免登录<br>
	<input type="submit" value="登录">
	<input type="reset" value="重置">
</form>
</body>
</html>
