package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClient {
	private JedisPool pool;  
    
    public void setPool(JedisPool pool) {  
        this.pool = pool;  
    }  
    /** 
     * 获取jedis  
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
     * 关闭连接 
     * @param jedis 
     */  
    public void disconnect(Jedis jedis){  
        jedis.disconnect();  
    }  
    /** 
     * 将jedis 返还连接池 
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
     * 无法返还jedispool，释放jedis客户端对象， 
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
