package com.euphe.action;

import com.euphe.service.DBService;
import com.euphe.util.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("loginUserAction")
public class LoginUserAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(LoginUserAction.class);
    /*
     * 使用指定类初始化日志对象
     * 在日志输出的时候，可以打印出日志信息所在类
     * 如：Logger logger = LoggerFactory.getLogger(com.Book.class);
     * logger.debug("日志信息");将会打印出: com.Book : 日志信息
     */
    @Resource
    private DBService dBService;


    private String password;
    private String username;


    public void login(){
        log.info("User:{} 正在登陆系统...",username);
        try{
            boolean flag = dBService.getLoginUser(username, password);
            Utils.write2PrintWriter(flag);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    /**
     * @return the dBService
     */
    public DBService getdBService() {
        return dBService;
    }
    /**
     * @param dBService the dBService to set
     */
    public void setdBService(DBService dBService) {
        this.dBService = dBService;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
