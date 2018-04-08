package com.euphe.util;

import com.euphe.model.ObjectInterface;
import com.euphe.service.DBService;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.ContextLoader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


public class Utils {
    private static PrintWriter writer = null;
    private static ResourceBundle resb = null;

    private static int counter=0;// 在任务运行时返回递增的点字符串

    public static String getKey(String key,boolean dbOrFile){
        if(dbOrFile){
            DBService dbService =(DBService)SpringUtil.getBean("dBService");
            return dbService.getHConstValue(key);
        }
        if(resb==null){
            Locale locale = new Locale("zh", "CN");
            resb = ResourceBundle.getBundle("util", locale);
        }
        return resb.getString(key);
    }

    /**
     * 简单日志
     *
     * @param msg
     */
    public static void simpleLog(String msg) {
        System.out.println(new java.util.Date() + ":" + msg);
    }

    /**
     * 获得表的实体类的全路径
     *
     * @param tableName
     * @return
     */
    public static String getEntityPackages(String tableName) {
        return "com.euphe.model." + tableName;
    }

    /**
     * 根据类名获得实体类
     *
     * @param tableName
     * @param json
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    public static Object getEntity(String tableName, String json) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, JsonParseException, JsonMappingException, IOException {
        Class<?> cl = Class.forName(tableName);// 根据全类名创建对应的实体类对象
        ObjectInterface o = (ObjectInterface) cl.newInstance();// 利用了多态
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();// 转换json时需要的对象
        try {
            // convert JSON string to Map
            map = mapper.readValue(json, Map.class);
            return o.setObjectByMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向PrintWriter中输入数据
     *
     * @param info
     */
    public static void write2PrintWriter(String info) {
        try {
            ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
            writer = ServletActionContext.getResponse().getWriter();

            writer.write(info);// 响应输出
            // 释放资源，关闭流
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param flag
     */
    public static void write2PrintWriter(boolean flag) {
        write2PrintWriter(String.valueOf(flag));
    }

    /**
     * 获得根路径
     * @return
     */
    private static String getRootPath(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
    }

    /**
     * 获得根路径下面的subPath路径
     * @param subPath
     * @return
     */
    public static String getRootPathBasedPath(String subPath){
        return getRootPath()+subPath;
    }

    /**
     * 获得递增点字符串
     * @return
     */
    public static String getDotState(String pre){
        counter++;
        StringBuffer buff =new StringBuffer();
        for(int i=0;i<counter;i++){
            buff.append(".");
        }
        if(counter>=7){
            counter=0;
        }
        return pre+buff.toString();
    }
}
