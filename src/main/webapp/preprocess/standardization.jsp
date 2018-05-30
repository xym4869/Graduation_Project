<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<body>
<div style="text-align:center;padding-left: 30px;font-size: 30px;padding-top:10px;">数据预处理</div>
<br>
<div style="padding-left: 28%;font-size: 15px;padding-top:10px;"><br>
    在Hadoop集群上面进行,如果有MR任务监控页面，请先关闭，再提交本次MR任务。<br>
    此处对原文件做了如下操作：<br>
    <ul>
        <li style="font-size:14px">数据清洗（格式标准化，缺失值填补，离群点检测）</li>
        <li style="font-size:14px">数据集成（清除冗余数据）</li>
        <li style="font-size:14px">数据归约（属性子集选择）</li>
        <li style="font-size:14px">数据变换（属性构造）</li>
    </ul>
</div>
<div style="padding-left: 35%;padding-top:10px;">
    <table>
        <tr>
            <td><%--@declare id="name"--%><label for="name" style="font-size:13px">输入路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="standard_input_id" data-options="required:true" style="width:300px"
                       value="/minipre/part*" /></td>

        </tr>
        <tr>
            <td><label for="name" style="font-size:13px">输出路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="standard_output_id" data-options="required:true" style="width:300px"
                       value="/minpre_result" /></td>

        </tr>
        <tr>
            <td></td>
            <td><a id="standard_submit_id" href="" class="easyui-linkbutton"
                   data-options="iconCls:'icon-door_in'">预处理</a></td>
        </tr>

    </table>
</div>
<script type="text/javascript" src="js/preprocess.js"></script>
</body>

