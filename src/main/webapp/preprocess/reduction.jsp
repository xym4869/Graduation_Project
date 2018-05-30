<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<body>
<div style="text-align:center;padding-left: 30px;font-size: 30px;padding-top:10px;">数据集成——文件合并</div>
<br>
<div style="text-align:center;padding-left: 30px;font-size: 15px;padding-top:10px;"><br>
    在Hadoop集群上面进行,如果有MR任务监控页面，请先关闭，再提交本次MR任务。<br>
    多个文件内数据按统一的格式合并集成<br>
</div>
<div style="padding-left: 35%;padding-top:10px;">
    <table>
        <tr>
            <td><%--@declare id="name"--%><label for="name" style="font-size:13px">输入路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="reduc_input_id" data-options="required:true" style="width:300px"
                       value="/minirec/test*.log" /></td>

        </tr>
        <tr>
            <td><label for="name" style="font-size:13px">输出路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="reduc_output_id" data-options="required:true" style="width:300px"
                       value="/minipre" /></td>

        </tr>
        <tr>
            <td></td>
            <td><a id="reduc_submit_id" href="" class="easyui-linkbutton"
                   data-options="iconCls:'icon-door_in'">集成</a></td>
        </tr>

    </table>
</div>
<script type="text/javascript" src="js/preprocess.js"></script>
</body>

