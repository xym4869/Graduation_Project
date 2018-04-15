/**
 * 预处理部分js
 *
 */
$(function () {
    // =====uploadId,数据上传button绑定 click方法
    $('#uploadId').bind('click', function(){
        var input_i=$('#localFileId').val();
        // 弹出进度框
        popupProgressbar('数据上传','数据上传中...',1000);
        // ajax 异步提交任务
        callByAJax('cloud/cloud_upload.action',{input:input_i});
    });

    // ===== 数据下载
    $('#downloadId').bind('click', function(){
        var input_=$('#fromHDFSFileId').val();
        var output_=$('#tolocalFileId').val();
        // 弹出进度框
        popupProgressbar('数据下载','数据下载中...',1000);
        // ajax 异步提交任务
        callByAJax('cloud/cloud_download.action',{input:input_,output:output_});
    });

    // =====数据标准化处理
    $('#standard_submit_id').bind('click', function(){
        var input_i=$('#standard_input_id').val();
        var output_i=$('#standard_output_id').val();
        // 弹出进度框
        popupProgressbar('提交任务','提交任务到云平台中...',1000);
        // ajax 异步提交任务
        callByAJax('cloud/cloud_standard.action',{input:input_i,output:output_i});
    });

    // ===== 解析入库
    $('#resolveId').bind('click', function(){
        var input_=$('#resolveFileId').val();
        // 弹出进度框
        popupProgressbar('数据入库','数据解析入库中...',1000);
        // ajax 异步提交任务
        callByAJax('cloud/cloud_resolve2db.action',{input:input_});
    });

    }
)

/**
 * 刷新，对应前端页面中的var monitor_cf_interval= setInterval("monitor_one_refresh()",3000);
 */
function monitor_one_refresh(){
    $.ajax({ // ajax提交
        url : 'cloud/cloud_monitorone.action',
        dataType : "json",
        success : function(data) {
            if (data.finished == 'error') {// 获取信息错误 ，返回数据设置为0，否则正常返回
                clearInterval(monitor_cf_interval);
                setJobInfoValues(data);
                console.info("monitor,finished:"+data.finished);
                $.messager.show({
                    title : '提示',
                    msg : '任务运行失败！'
                });
            } else if(data.finished == 'true'){
                // 所有任务运行成功则停止timer
                console.info('monitor,data.finished='+data.finished);
                setJobInfoValues(data);
                clearInterval(monitor_cf_interval);
                $.messager.show({
                    title : '提示',
                    msg : '所有任务成功运行完成！'
                });

            }else{
                // 设置提示，并更改页面数据,多行显示job任务信息
                setJobInfoValues(data);
            }
        }
    });

}

function setJobInfoValues(data){
    $('#jobnums').val(data.jobnums);
    $('#currjob').val(data.currjob);
    $('#jobid').val(data.rows.jobId);
    $('#jobname').val(data.rows.jobName);

//	(n*100).toFixed(2)+"%" // 保留两位小数，同时转为百分数
    $('#mapprogress').val((data.rows.mapProgress*100).toFixed(2)+'%');
    $('#redprogress').val((data.rows.redProgress*100).toFixed(2)+'%');
    $('#state').val(data.rows.runState);
}
