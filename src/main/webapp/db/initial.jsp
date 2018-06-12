<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- request.getScheme() 返回当前链接使用的协议；一般应用返回http;SSL返回https -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'init.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<div data-options="region:'north',border:false"
		style="height:60px;padding:50px;">

		<br><br><br> 
		<select id="cc" class="easyui-combobox" name="dept"
			style="width:200px;">
			<option value="LoginUser">用户登录表</option>
			<option value="HConstants">集群配置表</option>

		</select> 
	
		<a id="initialId" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">初始化</a> <br>

		<br>
		<br>
	</div>
	<script type="text/javascript" src="js/initial.js"></script>
</body>
</html>
