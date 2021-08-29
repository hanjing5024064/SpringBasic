<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<title>SpringSecurity安全框架</title>
<base href="<%=basePath%>" />
</head>
<body>
<h2>注销成功，欢迎您再来！</h2>
<h3>更多内容请访问<a href="http://www.mldn.cn">MLDN-魔乐科技</a></h3> 
</body>
</html>
