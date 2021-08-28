<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>" />
<h3>雇员姓名：${myemp.ename}、基本工资：${myemp.salary}、所在部门名称：${myemp.dept.dname}</h3>
