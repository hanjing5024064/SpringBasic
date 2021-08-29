<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String logout_url = basePath + "mldn-logout" ; // 注销路径
%>
<title>SpringSecurity安全框架</title>
<base href="<%=basePath%>" />
</head>
<body>
<security:authorize access="isAuthenticated()">		<!-- 是否为认证过的用户 -->
	用户已经成功登录了！
</security:authorize>
<security:authorize access="hasRole('USER')">		<!-- 是否拥有“USER”角色 -->
	拥有USER角色
</security:authorize>
<security:authorize access="hasRole('ADMIN')">		<!-- 是否拥有“ADMIN”角色 -->
	拥有ADMIN角色
</security:authorize>
<h2>登录成功，欢迎“<security:authentication property="principal.username"/>”回来，
也可以选择<a href="<%=logout_url%>">注销</a>！</h2>
<h3>更多内容请访问<a href="http://www.mldn.cn">MLDN-魔乐科技</a></h3>
<img src="mvcimages/mldn.png" style="width:500px;">
<img src="mvcimages/jixianit.png" style="width:500px;">
</body>
</html>
