package com.taotao.order.dao;

/**
 * @Author: upsmart
 * @Description: 设置redis客户端接口不管是集群还是单机
 * @Date: Created by 下午2:03 on 17-12-11.
 * @Modified By:
 */
public interface JedisClient {

    String get(String key);
    String set(String key, String value);
    String hget(String hkey, String key);
    long hset(String hkey, String key, String value);
    long hdel(String hkey, String key);
    long incr(String key);
    long expire(String key, int second);
    long ttl(String key);
}
