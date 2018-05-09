var editFlag = undefined;
$(function() {

    // logdataid
    $('#logdataid').datagrid(
        {
            border : false,
            // fitColumns : false,
            singleSelect : true,
            width : 600,
            height : 250,
            nowrap : false,
            fit : true,
            pagination : true,// 分页控件
            pageSize : 10, // 每页记录数，需要和pageList保持倍数关系
            pageList : [ 10, 30, 60, 100 ],
            rownumbers : true,// 行号
            pagePosition : 'top',
            url : 'dB/dB_getTableData.action',
            queryParams: {
                tableName: 'LogData' //,
                //	subject: 'datagrid'
            },
            idField:'id',
            columns :[[
                {
                    field : 'time',
                    title : '时间',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {required: true//设置编辑规则属性
                        }
                    },
                    formatter: function(value, row, index) {
                        return new Date(value).toISOString();
                    }
                },
                {
                    field : 'mAC',
                    title : 'MAC',
                    //width : '140',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'vPNIP',
                    title : 'VPNIP',
                    //width : '130',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'websiteIP',
                    title : 'WebsiteIP',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'userIP',
                    title : 'UserIP',
                   // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'country',
                    title : 'Country',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'region',
                    title : 'Region',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'city',
                    title : 'City',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'domainKey1',
                    title : '域名关键字1',
                   // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'dK1type',
                    title : '域名关键字1的类型',
                    // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'domainKey2',
                    title : '域名关键字2',
                   // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'dK2type',
                    title : '域名关键字2的类型',
                    // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'domainKey3',
                    title : '域名关键字3',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'dK3type',
                    title : '域名关键字3的类型',
                    // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'osFamily',
                    title : '操作系统家族',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'osName',
                    title : '操作系统详细名称',
                   // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'uaFamily',
                    title : '浏览器名称',
                   // width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'browserVersionInfo',
                    title : '浏览器版本',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'deviceType',
                    title : '设备类型',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'type',
                    title : '类型',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'protocol',
                    title : '协议',
                    //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }},
                {
                    field : 'host',
                    title : '主机名',
                   //width : '150',
                    editor: {//设置其为可编辑
                        type: 'validatebox',//设置编辑样式 自带样式有：text，textarea，checkbox，numberbox，validatebox，datebox，combobox，combotree 可自行扩展
                        options: {
                            required: true//设置编辑规则属性
                        }
                    }}

            ]],
            toolbar: [{//在dategrid表单的头部添加按钮
                text: "添加",
                iconCls: "icon-add",
                handler: function () {
                    if (editFlag != undefined) {
                        $("#logdataid").datagrid('endEdit', editFlag);//结束编辑，传入之前编辑的行
                    }
                    if (editFlag == undefined) {//防止同时打开过多添加行
                        $("#logdataid").datagrid('insertRow', {//在指定行添加数据，appendRow是在最后一行添加数据
                            index: 0,    // 行数从0开始计算
                            row: {
                                time: '请输入时间:',
                                MAC: '请输入MAC:',
                                VPNIP: '请输入VPNIP:',
                                websiteIP: '请输入WebsiteIP:',
                                userIP: '请输入UserIP:',
                                country: '请输入国家：',
                                region: '请输入省份',
                                city: '请输入城市',
                                domainKey1: '请输入域名关键字1:',
                                DK1type: '请输入域名关键字1的类型',
                                domainKey2: '请输入域名关键字2:',
                                DK2type: '请输入域名关键字1的类型',
                                domainKey3: '请输入域名关键字3:',
                                DK3type: '请输入域名关键字1的类型',
                                osFamily: '请输入操作系统家族:',
                                osName: '请输入操作系统名称:',
                                uaFamily: '请输入浏览器名称:',
                                browserVersionInfo: '请输入浏览器版本:',
                                type: '请输入类型:',
                                protocol: '请输入协议：',
                                host: '请输入主机名:'
                            }
                        });
                        $("#logdataid").datagrid('beginEdit', 0);//开启编辑并传入要编辑的行
                        editFlag = 0;
                    }
                }
            }, '-', {//'-'就是在两个按钮的中间加一个竖线分割，看着舒服
                text: "删除",
                iconCls: "icon-remove",
                handler: function () {
                    //选中要删除的行
                    var row = $("#logdataid").datagrid('getSelected');
                    var rowIndex = $('#logdataid').datagrid('getRowIndex', row);
                    console.info(row+","+rowIndex);
                    if (row.id) {//选中几行的话触发事件
                        deleteRow(row.id,rowIndex);
//				                        $.messager.confirm("提示", "您确定要删除数据吗？", function (res) {//提示是否删除
//				                            if (res) {
//				                                deleteRow(row.id);
//				                            }
//				                        });
                    }
                }
            }, '-', {
                text: "修改",
                iconCls: "icon-edit",
                handler: function () {
                    //选中一行进行编辑
                    var rows = $("#logdataid").datagrid('getSelections');
                    if (rows.length == 1) {//选中一行的话触发事件
                        if (editFlag != undefined) {
                            $("#logdataid").datagrid('endEdit', editFlag);//结束编辑，传入之前编辑的行
                        }
                        if (editFlag == undefined) {
                            var index = $("#logdataid").datagrid('getRowIndex', rows[0]);//获取选定行的索引
                            $("#logdataid").datagrid('beginEdit', index);//开启编辑并传入要编辑的行
                            editFlag = index;
                        }
                    }
                }
            }, '-', {
                text: "保存",
                iconCls: "icon-save",
                handler: function () {
                    $("#logdataid").datagrid('endEdit', editFlag);
                }
            }, '-', {
                text: "撤销",
                iconCls: "icon-redo",
                handler: function () {
                    editFlag = undefined;
                    $("#logdataid").datagrid('rejectChanges');
                }
            }, '-'],
            onAfterEdit: function (rowIndex, rowData, changes) {//在添加完毕endEdit，保存时触发
                console.info(rowData);//在火狐浏览器的控制台下可看到传递到后台的数据，这里我们就可以利用这些数据异步到后台添加，添加完成后，刷新datagrid
                saveRow(rowIndex,rowData);
                editFlag = undefined;//重置
            }, onDblClickCell: function (rowIndex, field, value) {//双击该行修改内容
            if (editFlag != undefined) {
                $("#logdataid").datagrid('endEdit', editFlag);//结束编辑，传入之前编辑的行
            }
            if (editFlag == undefined) {
                $("#logdataid").datagrid('beginEdit', rowIndex);//开启编辑并传入要编辑的行
                editFlag = rowIndex;
            }
        }
        });
    //logdataid
});


function deleteRow(index,rowIndex){
    $.messager.confirm('Confirm','确认删除?',function(r){
        if (r){
            $.ajax({
                url : 'dB/dB_deleteById.action',
                data: {id:index,tableName:'logdata'},
                type : 'GET',
                timeout : 60000,
                success : function(data, textStatus, jqXHR) {
                    var msg = '删除';
                    if(data == 'success') {  //
                        console.info(index);

                        $.messager.alert('提示', msg + '成功！', 'info', function() {
                            $("#logdataid").datagrid('deleteRow', rowIndex);
                        });
                        return;
                    } else {
                        $.messager.alert('提示', msg + '失败！', 'error', function() {
                        });
                    }
                }
            });

        }
    });
}

function saveRow(index,node){
    var json = {id:node.id, time:node.time, MAC:node.MAC, VPNIP:node.VPNIP,websiteIP:node.websiteIP, userIP:node.userIP,
    domainKey1:node.domainKey1,DK1type:node.DK1type, domainKey2:node.domainKey2, DK2type:node.DK2type, DK3type:node.DK3type,
        domainKey3:node.domainKey3, country:node.country,region:node.region,
        city:node.city, osFamily:node.osFamily, osName:node.osName, uaFamily:node.uaFamily, browserVersionInfo:node.browserVersionInfo,
        type:node.type, protocol:node.protocol,host:node.host};

    var encodeJson=JSON.stringify(json);
    $.ajax({
        url : 'dB/dB_updateOrSave.action',
        type : 'POST',
        data : {json:encodeJson,tableName:"logdata"},
        timeout : 60000,
        success : function(data, textStatus, jqXHR) {
            var msg = '';
            if (data == "success") {
                console.info('保存成功！');
                $.messager.alert('提示', '保存成功！', 'info', function() {
                    $("#logdataid").datagrid('refreshRow', index);
                    $("#logdataid").datagrid('reload');
                });
            } else{
                msg = "保存失败！";
                console.info(msg);
                $.messager.alert('提示', msg , 'error', function() {
                    $("#logdataid").datagrid('beginEdit', index);
                });
            }

        }
    });

}