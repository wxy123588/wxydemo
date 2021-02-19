package com.example.demo.redis;

import java.util.Map;

public interface IRedisService {

    // 加入元素
    void setValue(String key, Map<String, Object> value);
    // 加入元素
    void setValue(String key, String value);
    // 加入元素
    void setValue(String key, Object value);
    // 获取元素
    Object getValue(String key);
    public boolean set(final String key, final String value);
    public String get(final String key);
    //设置有效天数
    public boolean expire(final String key, long expire) ;
    //移除数据
    public boolean remove(final String key) ;
}