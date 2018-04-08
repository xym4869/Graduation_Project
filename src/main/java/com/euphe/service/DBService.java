package com.euphe.service;

import com.euphe.dao.BaseDAO;
import com.euphe.model.HConstants;
import com.euphe.model.LoginUser;
import com.euphe.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库service
 *
 */
@Service("dBService")
public class DBService {
    private Logger log = LoggerFactory.getLogger(DBService.class);
    @Resource
    private BaseDAO<Object> baseDao;

    /**
     * 用户登录检查
     *
     * @param username
     * @param password
     * @return
     */
    public boolean getLoginUser(String username, String password) {
        String hql = "from LoginUser lu where lu.username='" + username + "'";
        List<Object> lus = baseDao.find(hql);
        if (lus.size() < 1) {
            log.info("没有此用户，username：{}", username);
            return false;
        }
        if (lus.size() > 1) {
            log.info("登录检查多个重名用户，请检查数据库，用户名为：{}", username);
            return false;
        }
        LoginUser lu = (LoginUser) lus.get(0);
        if (lu.getPassword().equals(password)) {
            log.info("用户：'" + username + "' 登录成功！");
            return true;
        }
        return false;
    }

    /**
     * 测试表中是否有数据
     *
     * @param tableName
     * @return
     */
    public boolean getTableData(String tableName) {
        String hql = "from " + tableName + " ";
        List<Object> td = baseDao.find(hql);
        if (td.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获得tableName的所有数据并返回
     *
     * @param tableName
     * @return
     */
    public List<Object> getTableAllData(String tableName) {
        String hql = "from " + tableName + " ";
        List<Object> list = null;
        try {
            list = baseDao.find(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 分页获取tableName 所有数据
     *
     * @param tableName:类实体名
     * @param rows
     * @param page
     * @return
     */
    public Map<String, Object> getTableData(String tableName, int rows, int page) {
        String hql = "from " + tableName;
        String hqlCount = "select count(1) from " + tableName;
        List<Object> list = baseDao.find(hql, new Object[] {}, page, rows);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", baseDao.count(hqlCount));
        jsonMap.put("rows", list);
        return jsonMap;
    }

    /**
     * 保存数据
     *
     * @param list
     * @return
     */
    public boolean saveTableData(List<Object> list) {

        try {
            baseDao.saveBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deleteById(String tableName, String id) {
        String hql = "delete " + tableName + "  tb where tb.id='" + id + "'";
        try {
            Integer ret = baseDao.executeHql(hql);
            log.info("删除表{},删除了{}条记录！", new Object[] { tableName, ret });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTable(String tableName) {
        String hql = "delete " + tableName;
        try {
            Integer ret = baseDao.executeHql(hql);
            log.info("删除表{},删除了{}条记录！", new Object[] { tableName, ret });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新或者插入表 不用每个表都建立一个方法，这里根据表名自动装配
     *
     * @param tableName
     * @param json
     * @return
     */
    public boolean updateOrSave(String tableName, String json) {
        try {
            // 根据表名获得全类名并创建相应的实体类，并赋值
            Object o = Utils.getEntity(Utils.getEntityPackages(tableName), json);
            baseDao.saveOrUpdate(o);
            log.info("保存表{}！", new Object[] { tableName });
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获得Hadoop集群配置
     *
     * @param key
     * @return
     */
    public String getHConstValue(String key) {
        HConstants hc = null;
        try {
            hc = (HConstants) baseDao.find("from HConstants hc where hc.custKey='" + key + "'").get(0);
            if (hc == null) {
                log.info("Hadoop基础配置表找不到配置的key：{}", key);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取云平台配置信息出错，key：" + key);
        }
        return hc.getCustValue();
    }

    /**
     * 初始化登录表
     *
     * @return
     */
    public boolean insertLoginUser() {
        try {
            baseDao.executeHql("delete LoginUser");
            baseDao.save(new LoginUser("admin", "admin"));
            baseDao.save(new LoginUser("test", "test"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 初始化HConstants
     *
     * @return
     */
    public boolean insertHConstants() {// 初始化hadoop集群表（需要将其初始化为自己的hadoop配置信息）
        try {
            baseDao.executeHql("delete HConstants");
            baseDao.save(new HConstants("mapreduce.app-submission.cross-platform", "true", "Whether to submit tasks across platforms"));
            baseDao.save(new HConstants("fs.defaultFS", "hdfs://hadoop:9000", "Namenode host and port"));
            baseDao.save(new HConstants("mapreduce.framework.name", "yarn", "Mapreduce usage configuration"));
            baseDao.save(new HConstants("yarn.resourcemanager.address", "hadoop:8032", "ResourceManager host and port"));
            baseDao.save(new HConstants("yarn.resourcemanager.scheduler.address", "hadoop:8030", "Scheduler host and port"));
            baseDao.save(new HConstants("mapreduce.jobhistory.address", "hadoop:10020", "JobHistory host and port"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
