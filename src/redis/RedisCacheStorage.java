package redis;

import java.util.Map;

import net.sf.json.JSONObject;

public interface RedisCacheStorage<K,V> {
	public void setRedisClient(RedisClient redisClient);
	/** 
     * 在redis数据库中插入 key  和value 
     
     */  
    boolean set(K key,V value);  
    
    boolean set(K key,int value);
    /** 
     * 在redis数据库中插入 key  和value 并且设置过期时间 
     
     */  
    boolean set(K key, V value, int exp);  
    /** 
     * 根据key 去redis 中获取value 
    
     */  
   String getStr(String jKey);
   Object get(K key);  
    /** 
     * 删除redis库中的数据 
     
     */  
    boolean remove(K key);  
    /** 
     * 设置有序集合类型数据到redis 数据库 
     */  
    boolean zadd(String key, double score, String member); 
    /** 
     * 设置哈希类型数据到redis 数据库 
     */  
    boolean hset(String cacheKey,K key,V value);  
    /** 
     * 获取哈希表数据类型的值 
     * @param cacheKey 
     */  
    Object hget(String cacheKey,K key);  
    /** 
     * 获取哈希类型的数据 
     */  
    //Map<K,V> hget(String cacheKey); */ 
}
