package com.euphe.model;

import java.util.Map;

/**
 * Created by EuphemiaShaw on 2018/4/4.
 */
public interface ObjectInterface {
    /**
     * 这里根据表名实现属性的自动装配，避免每个实体类都设一个方法
     * @param map
     * @return
     */
    public  Object setObjectByMap(Map<String,Object> map);
}
