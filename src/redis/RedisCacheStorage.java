package redis;

import java.util.Map;

import net.sf.json.JSONObject;

public interface RedisCacheStorage<K,V> {
	public void setRedisClient(RedisClient redisClient);
	/** 
     * ��redis���ݿ��в��� key  ��value 
     
     */  
    boolean set(K key,V value);  
    
    boolean set(K key,int value);
    /** 
     * ��redis���ݿ��в��� key  ��value �������ù���ʱ�� 
     
     */  
    boolean set(K key, V value, int exp);  
    /** 
     * ����key ȥredis �л�ȡvalue 
    
     */  
   String getStr(String jKey);
   Object get(K key);  
    /** 
     * ɾ��redis���е����� 
     
     */  
    boolean remove(K key);  
    /** 
     * �������򼯺��������ݵ�redis ���ݿ� 
     */  
    boolean zadd(String key, double score, String member); 
    /** 
     * ���ù�ϣ�������ݵ�redis ���ݿ� 
     */  
    boolean hset(String cacheKey,K key,V value);  
    /** 
     * ��ȡ��ϣ���������͵�ֵ 
     * @param cacheKey 
     */  
    Object hget(String cacheKey,K key);  
    /** 
     * ��ȡ��ϣ���͵����� 
     */  
    //Map<K,V> hget(String cacheKey); */ 
}
