<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<body>
<div style="text-align:center;padding-left: 30px;font-size: 30px;padding-top:10px;">数据过滤</div>

<div style="padding-left: 35%;font-size: 15px;padding-top:10px;"><br>
    结果数据过滤处理包括：<br>
    <ol>
        <li style="font-size:14px">过滤结果数据中符合规则的数据</li>
        <li style="font-size:14px">过滤规则 ：存在域名关键字且设备信息不全为空</li>
        <li style="font-size:14px">把符合规则的LogData数据写入HDFS</li>
    </ol>
</div>
<br>
<div style="padding-left: 35%;padding-top:10px;">
    <table>
        <tr>
            <td><%--@declare id="name"--%><label for="name" style="font-size:13px">输入路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="preprocess_input_id" data-options="required:true"
                       style="width:300px" value="/minipre_result/part*" /></td>
        <tr>
            <td><%--@declare id="name"--%><label for="name" style="font-size:13px">输出路径:</label>
            </td>
            <td><input class="easyui-validatebox" type="text"
                       id="preprocess_output_id" data-options="required:true" style="width:300px"
                       value="/miniFinalRes" /></td>

        </tr>
        <%--<tr>--%>
            <%--<td><label for="name">生成文件个数:</label>--%>
            <%--</td>--%>
            <%--<td><input class="easyui-validatebox" type="text"--%>
                       <%--id="preprocess_record_id" data-options="required:true" style="width:300px"--%>
                       <%--value="3" /></td>--%>

        </tr>
        <tr>
            <td></td>
            <td><a id="preprocess_submit_id" href="" class="easyui-linkbutton"
                   data-options="iconCls:'icon-door_in'">过滤</a></td>
        </tr>

    </table>
</div>
<script type="text/javascript" src="js/preprocess.js"></script>
</body>

