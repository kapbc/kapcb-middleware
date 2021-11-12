package com.kapcb.framework.middleware.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <a>Title: IRedisService </a>
 * <a>Author: Kapcb <a>
 * <a>Description: IRedisService <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/12 22:12
 */
public interface IRedisService {

    /**
     * expire time
     *
     * @param key  String
     * @param time Long
     * @return boolean
     */
    boolean expire(String key, Long time);

    /**
     * 根据key获取当前对象的过期时间
     * 返回 0 代表永久有效
     * key 不能为空
     *
     * @param key String
     * @return Long
     */
    long getExpire(String key);

    /**
     * judgement whather has the value of the key
     *
     * @param key String
     * @return boolean
     */
    boolean hasKey(String key);

    /**
     * delete redis cache
     *
     * @param key String...
     */
    void delete(String... key);

    /**
     * delete redis cache
     *
     * @param key String
     */
    void delete(String key);

    /**
     * multiple delete
     *
     * @param keys List<String></String>
     */
    void multiDelete(List<String> keys);

    /**
     * get value
     *
     * @param key String
     * @return Object
     */
    Object get(String key);

    /**
     * @param keys List<String>
     * @return List<Object>
     */
    List<Object> getMulti(List<String> keys);

    /**
     * set into
     *
     * @param key   String
     * @param value Object
     * @return boolean
     */
    boolean set(String key, Object value);

    /**
     * multiple set
     *
     * @param keyValueMap Map<String, String>
     * @return boolean
     */
    boolean set(Map<String, String> keyValueMap);

    /**
     * multiple set
     *
     * @param keyValueMap Map<String, List<Object>>
     * @return boolean
     */
    boolean multiSet(Map<String, List<Object>> keyValueMap);

    /**
     * put the cache with expire time
     *
     * @param key   String
     * @param value Object
     * @param time  Long
     * @return boolean
     */
    boolean set(String key, Object value, Long time);

    /**
     * 递增
     *
     * @param key   String
     * @param delta Long
     * @return Long
     */
    Long increase(String key, Long delta);

    /**
     * 递减
     *
     * @param key   String
     * @param delta Long
     * @return Long
     */
    Long decrement(String key, Long delta);

    /**
     * get hash
     *
     * @param key  String
     * @param item String
     * @return Object
     */
    Object getHash(String key, String item);

    /**
     * get multiple hash of all the key that value is : key
     *
     * @param key String
     * @return Map<Object, Object>
     */
    Map<Object, Object> getMultipleHash(String key);

    /**
     * set hash if not exist it will be create
     *
     * @param key   String
     * @param item  String
     * @param value Object
     * @return boolean
     */
    boolean setHash(String key, String item, Object value);

    /**
     * set hash with expire time if not exist it will be create
     *
     * @param key   String
     * @param item  String
     * @param value Object
     * @param time  Long
     * @return boolean
     */
    boolean setHashWithExpireTime(String key, String item, Object value, Long time);

    /**
     * set multiple hash
     *
     * @param key   String
     * @param value Map<String, Object>
     * @return boolean
     */
    boolean setMultipleHash(String key, Map<String, Object> value);

    /**
     * set multiple hash with expire time
     *
     * @param key   String
     * @param value Map<String, Object>
     * @param time  Long
     * @return boolean
     */
    boolean setMultipleHashWithExpireTime(String key, Map<String, Object> value, Long time);

    /**
     * delete hash in redis
     *
     * @param key  String
     * @param item Object...
     */
    void deleteHash(String key, Object... item);

    /**
     * judgement whether has the hash key in the redis
     *
     * @param key  String
     * @param item String
     * @return boolean
     */
    boolean hasHashKey(String key, String item);

    /**
     * hash increase , if not exist it will be create, and return the result after create value add by
     *
     * @param key  String
     * @param item String
     * @param by   Double
     * @return Double
     */
    Double hashIncrease(String key, String item, Double by);

    /**
     * hash decrement
     *
     * @param key  String
     * @param item String
     * @param by   Double
     * @return Double
     */
    Double hashDecrement(String key, String item, Double by);

    /**
     * get the key of the all set value
     *
     * @param key String
     * @return Set<Object>
     */
    Set<Object> getSet(String key);

    /**
     * judgement whether has the set key in the redis
     *
     * @param key   String
     * @param value Object
     * @return boolean
     */
    boolean hasSetKey(String key, Object value);

    /**
     * put into set
     * the return value is add success value
     *
     * @param key    String
     * @param values Object...
     * @return Long
     */
    Long setSet(String key, Object... values);

    /**
     * put into set with expire time
     *
     * @param key    String
     * @param time   Long
     * @param values Object...
     * @return Long
     */
    Long setSetWithExpireTime(String key, Long time, Object... values);

    /**
     * get set size in redis
     *
     * @param key String
     * @return Long
     */
    Long getSetSize(String key);

    /**
     * remove the value is values in set
     *
     * @param key    String
     * @param values Object...
     * @return Long
     */
    Long removeSet(String key, Object... values);

    /**
     * get list result in redis
     *
     * @param key   String
     * @param start Long
     * @param end   Long
     * @return Object
     */
    Object getList(String key, Long start, Long end);

    /**
     * get list size in redis
     *
     * @param key String
     * @return Long
     */
    Long getListSize(String key);

    /**
     * get list by index
     *
     * @param key   String
     * @param index Long
     * @return Object
     */
    Object getListByIndex(String key, Long index);

    /**
     * put list into redis
     *
     * @param key   String
     * @param value Object
     * @return boolean
     */
    boolean setList(String key, Object value);

    /**
     * put list into redis with expire time
     *
     * @param key   String
     * @param value Object
     * @param time  Long
     * @return boolean
     */
    boolean setListWithExpireTime(String key, Object value, Long time);

    /**
     * set list into redis
     *
     * @param key   String
     * @param value List<Object>
     * @return boolean
     */
    boolean setList(String key, List<Object> value);

    /**
     * set list with expire time
     *
     * @param key   String
     * @param value List<Object>
     * @param time  Long
     * @return boolean
     */
    boolean setListWithExpireTime(String key, List<Object> value, Long time);

    /**
     * update list by index
     *
     * @param key   String
     * @param index Long
     * @param value Object
     * @return boolean
     */
    boolean updateListByIndex(String key, Long index, Object value);
}
