<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String emp_add_url = basePath + "pages/emp/add.action" ;
%>
<base href="<%=basePath%>" />
<form action="<%=emp_add_url%>" method="post" enctype="multipart/form-data">
	雇员编号：<input type="text" name="empno" id="empno" value="7369"><br>
	雇员姓名：<input type="text" name="ename" id="ename" value="李兴华"><br>
	基本工资：<input type="text" name="salary" id="salary" value="3000.33"><br>
	雇佣日期：<input type="text" name="hiredate" id="hiredate" value="1991-11-11"><br>
	雇员等级：<input type="text" name="level" id="level" value="0"><br>
	部门编号：<input type="text" name="dept.deptno" id="dept.deptno" value="10"><br>
	部门名称：<input type="text" name="dept.dname" id="dept.dname" value="财务部"><br>
	部门成立：<input type="text" name="dept.createdate" id="dept.createdate" value="1986-09-15"><br>
	上传图片：<input type="file" name="photo" id="photo"><br>
	<input type="submit" value="添加"><input type="reset" value="重置">
</form>

