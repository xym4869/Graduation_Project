<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<body>
	<div  style="text-align:center;padding-left: 30px;font-size: 30px;padding-top:10px;">结果解析</div><br>
    <div style="padding-left: 35%;padding-top:10px;">

			<table>
				<tr>
					<td><%--@declare id="name"--%><label for="name" style="font-size:13px">输入路径:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="resolveFileId" data-options="required:true"  style="width:300px"
						value="WEB-INF/classes/standardizedData"/>
					</td>
					
				</tr>
				<tr>
					<td></td>
					<td>
					<a id="resolveId" href="" class="easyui-linkbutton" data-options="iconCls:'icon-door_in'">解析入库</a>
					</td>
				</tr>
				
			</table>
		</div>
	<script type="text/javascript" src="js/preprocess.js"></script>
</body>

