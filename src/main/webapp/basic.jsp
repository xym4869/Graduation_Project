<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Data Preprocess</title>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="js/basic.js"></script>
	<script type="text/javascript" src="js/loginuser.js"></script>
	
	
</head>

<body  class="easyui-layout">
	
	<div data-options="region:'north',border:false" style="height:80px;background:#B3DFDA;text-align: center">
	<h1 style="font-size: 30px">基于大数据的用户行为分析系统中预处理子系统</h1>
	</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:190px;padding:10px;">
		<ul id="navid" class="easyui-tree" data-options="url:'json/treeData.json',method:'get',animate:true,dnd:true"></ul>
	</div>
	
	<div  data-options="region:'center',title:'Center'">
		<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true" >
			<div title="Welcome" data-options="href:'about.html',iconCls:'icon-tip'"></div>
		</div>
	</div>
	<jsp:include page="login/login.jsp"></jsp:include>
</body>
</html>