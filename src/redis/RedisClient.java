package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClient {
	private JedisPool pool;  
    
    public void setPool(JedisPool pool) {  
        this.pool = pool;  
    }  
    /** 
     * ��ȡjedis  
     * @return 
     */  
    public Jedis getResource(){  
        Jedis jedis =null;  
        try {  
            jedis =pool.getResource();  
        } catch (Exception e) {  
           // LOG.info("can't  get  the redis resource");  
        }  
        return jedis;  
    }  
    /** 
     * �ر����� 
     * @param jedis 
     */  
    public void disconnect(Jedis jedis){  
        jedis.disconnect();  
    }  
    /** 
     * ��jedis �������ӳ� 
     * @param jedis 
     */  
    public void returnResource(Jedis jedis){  
        if(null != jedis){  
            try {  
                jedis.close();
            } catch (Exception e) {  
              //  LOG.info("can't return jedis to jedisPool");  
            }  
        }  
    }  
    /** 
     * �޷�����jedispool���ͷ�jedis�ͻ��˶��� 
     * @param jedis 
     */  
    public void brokenResource(Jedis jedis){  
        if (jedis!=null) {  
            try {  
            	 jedis.close(); 
            } catch (Exception e) {  
                //LOG.info("can't release jedis Object");  
            }  
        }  
    }  
}
