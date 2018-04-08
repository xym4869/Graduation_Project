package com.euphe.action;

import com.alibaba.fastjson.JSON;
import com.euphe.model.CurrentJobInfo;
import com.euphe.service.DBService;
import com.euphe.thread.Standard;
import com.euphe.util.HUtils;
import com.euphe.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CloudAction extends ActionSupport {
    /**
     * 处理hadoop任务的action
     */
    private static final long serialVersionUID = 1L;

    private String input;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public DBService getdBService() {
        return dBService;
    }

    public void setdBService(DBService dBService) {
        this.dBService = dBService;
    }

    private String output;

    @Resource
    private DBService dBService;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    /**
     * 单个任务监控
     * @throws IOException
     */
    public void monitorone() throws IOException {
        Map<String ,Object> jsonMap = new HashMap<String,Object>();
        List<CurrentJobInfo> currJobList =null;
        try{
            currJobList= HUtils.getJobs();
            jsonMap.put("jobnums", HUtils.JOBNUM);
            // 任务完成的标识是获取的任务个数必须等于jobNum，同时最后一个job完成
            // true 所有任务完成
            // false 任务正在运行
            // error 某一个任务运行失败，则不再监控

            if(currJobList.size()>=HUtils.JOBNUM){// 如果返回的list有JOBNUM个，那么才可能完成任务
                if("success".equals(HUtils.hasFinished(currJobList.get(currJobList.size()-1)))){
                    //currJobList.get(currJobList.size()-1)是获取最后一个任务的状态信息
                    jsonMap.put("finished", "true");
                    // 运行完成，初始化时间点
                    HUtils.setJobStartTime(System.currentTimeMillis());//当前任务完成，重新设定JobStartTime，以便下一个任务的判断
                }else if("running".equals(HUtils.hasFinished(currJobList.get(currJobList.size()-1)))){
                    jsonMap.put("finished", "false");
                }else{// fail 或者kill则设置为error
                    jsonMap.put("finished", "error");
                    HUtils.setJobStartTime(System.currentTimeMillis());
                }
            }else if(currJobList.size()>0){
                if("fail".equals(HUtils.hasFinished(currJobList.get(currJobList.size()-1)))||
                        "kill".equals(HUtils.hasFinished(currJobList.get(currJobList.size()-1)))){
                    jsonMap.put("finished", "error");
                    HUtils.setJobStartTime(System.currentTimeMillis());
                }else{
                    jsonMap.put("finished", "false");
                }
            }

            if(currJobList.size()==0){
                jsonMap.put("finished", "false");
            }else{
                if(jsonMap.get("finished").equals("error")){
                    CurrentJobInfo cj =currJobList.get(currJobList.size()-1);
                    cj.setRunState("Error!");
                    jsonMap.put("rows", cj);//放入job详细信息
                }else{
                    jsonMap.put("rows", currJobList.get(currJobList.size()-1));
                }
            }
            jsonMap.put("currjob", currJobList.size());
        }catch(Exception e){
            e.printStackTrace();
            jsonMap.put("finished", "error");
            HUtils.setJobStartTime(System.currentTimeMillis());
        }
        System.out.println(new java.util.Date()+":"+JSON.toJSONString(jsonMap));

        Utils.write2PrintWriter(JSON.toJSONString(jsonMap));// 使用JSON数据传输

        return ;
    }

    /**
     * 上传文件
     */
    public void upload(){
        Map<String,Object> map = HUtils.upload(input, HUtils.getHDFSPath(HUtils.SOURCEFILE));
        //HUtils.SOURCEFILE是文件上传到linux上的文件路径+文件名
        //getHDFSPath则获得相应的url路径

        Utils.write2PrintWriter(JSON.toJSONString(map));
        return ;
    }

    /**
     * 下载文件到本地文件夹
     */
    public void download(){
        // output 应该和HUtils.DEDUPLICATE_LOCAL保持一致

        Map<String,Object> map = HUtils.downLoad(input, Utils.getRootPathBasedPath(output));

        Utils.write2PrintWriter(JSON.toJSONString(map));
        return ;
    }

    /**
     * 标准化任务提交
     */
    public void standard(){
        Map<String ,Object> map = new HashMap<String,Object>();
        try{
            HUtils.setJobStartTime(System.currentTimeMillis()-10000);//设置任务开始时间
            //-10000是为了消除延时的影响，将任务提交时间提前,保证实际任务启动时间一定在JobStartTime之后。
            HUtils.JOBNUM=1;//设置任务数
            new Thread(new Standard(input,output)).start();//启动任务线程
            map.put("flag", "true");//任务启动完毕标志（不代表任务运行完成，仅仅是启动完毕）
            map.put("monitor", "true");//打开监控页面标志
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", "false");
            map.put("monitor", "false");
            map.put("msg", e.getMessage());
        }
        Utils.write2PrintWriter(JSON.toJSONString(map));
    }
}
