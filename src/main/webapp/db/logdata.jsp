<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<body>
<div  data-options="region:'north',border:false" style="height:60px;padding:50px 50px 10px 50px;">
    <h1> 日志数据表</h1><br><hr><br>
</div>
<table id="logdataid" class ="easyui-datagrid">

</table>


<script type="text/javascript" src="js/logdata.js"></script>
</body>

