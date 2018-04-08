package com.euphe.action;


import com.alibaba.fastjson.JSON;
import com.euphe.service.DBService;
import com.euphe.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("dBAction")
public class DBAction {
    /**
     * 处理基础数据的action
     */
    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(DBAction.class);
    @Resource
    private DBService dBService;
    private int rows;
    private int page;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String tableName;

    private String id;

    private String json;

    /**
     * 根据tableName分页获取表数据
     */
    public void getTableData(){
        Map<String,Object> list = dBService.getTableData(tableName,rows, page);
        String json = JSON.toJSONString(list);
        log.info(json);
        Utils.write2PrintWriter(json);
    }

    /**
     * 按照id删除表中数据
     */
    public void deleteById(){
        boolean delSuccess =dBService.deleteById(tableName, id);
        String msg="fail";
        if(delSuccess){
            msg="success";
        }
        log.info("删除表"+tableName+(delSuccess?"成功":"失败"+"!"));
        Utils.write2PrintWriter(msg);
    }

    /**
     * 更新或者保存数据
     */
    public void updateOrSave(){
        boolean delSuccess =dBService.updateOrSave(tableName, json);
        String msg="fail";
        if(delSuccess){
            msg="success";
        }
        log.info("保存表"+tableName+(delSuccess?"成功":"失败"+"!"));
        Utils.write2PrintWriter(msg);
    }

    /**
     * 初始化表
     */
    public void initialTable(){
        boolean initRet = false;
        if("LoginUser".equals(tableName)){//初始登录表
            initRet=dBService.insertLoginUser();
        }else if("HConstants".equals(tableName)){//初始化集群配置表
            initRet=dBService.insertHConstants();
        }else{
            //initRet = dBService.insertUserData();//初始用户表(未使用)
        }

        Utils.write2PrintWriter(initRet);
    }
}
