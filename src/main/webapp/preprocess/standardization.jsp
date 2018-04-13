<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<body>
<div style="padding-left: 30px;font-size: 20px;padding-top:10px;">数据文件标准化</div>
<br>
<div style="padding-left: 30px;font-size: 15px;padding-top:10px;"><br>
    数据清洗在Hadoop集群上面进行,如果有MR任务监控页面，请先关闭，再提交本次MR任务。<br>
    此处对原文件做了如下操作：数据规范化，缺失值填补，（时间）离群点检测，数据规约。<br>
</div>
<div style="padding-left: 30px;font-size: 20px;padding-top:10px;">
    <table>
        <tr>
            <td><%--@declare id="name"--%><label for="name">输入路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="standard_input_id" data-options="required:true" style="width:300px"
                       value="/minipre/testData.log" /></td>

        </tr>
        <tr>
            <td><label for="name">输出路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="standard_output_id" data-options="required:true" style="width:300px"
                       value="/minipre_result" /></td>

        </tr>
        <tr>
            <td></td>
            <td><a id="standard_submit_id" href="" class="easyui-linkbutton"
                   data-options="iconCls:'icon-door_in'">标准化</a></td>
        </tr>

    </table>
</div>
<script type="text/javascript" src="js/preprocess.js"></script>
</body>

