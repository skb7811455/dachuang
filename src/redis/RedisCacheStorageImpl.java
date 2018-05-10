package redis;

import java.util.Set;

import model.User;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

public class RedisCacheStorageImpl<V> implements RedisCacheStorage<String, V>{
	private RedisClient redisClient;
	private static final int EXPRIE_TIME =3600*24;   
	public boolean set(String key,int value){
		Jedis jedis =null;
		boolean isSucess =true;
		try {  
            //获取客户端对象  
          
            jedis =redisClient.getResource();  
            //执行插入  
        	//System.out.println(jedis);
            jedis.setex(key, EXPRIE_TIME, value+"");  
            //System.out.println(jedis.get(jKey));
        } catch (JedisException e) {  
            System.out.println("获取失败");
            isSucess =false;  
            if(null !=jedis){  
                //释放jedis对象  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if(isSucess){  
                //返还连接池  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }    
	}
	public String getStr(String jKey){
		Jedis jedis=null;
		String value=null;
		try {  
            //获取客户端对象  
            jedis =redisClient.getResource();  
            //执行查询  
            value =  jedis.get(jKey);  
            //判断值是否非空  
            if("".equals(value)){  
                return null;  
            }else{  
            	  
            	
            }  
            redisClient.returnResource(jedis);
            //返还连接池  
           // redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
              
            if(null !=jedis){  
                //释放jedis 对象  
                redisClient.brokenResource(jedis);  
            }  
        }  
		return value;
	}
    public void setRedisClient(RedisClient redisClient) {  
        this.redisClient = redisClient;  
    }  
    /** 
     * 在redis数据库中插入 key  和value 
     */  
    @Override  
    public boolean set(String key, V value) {  
        //设置默认过时时间  
        return set(key, value, EXPRIE_TIME);  
    }  
    /** 
     * 在redis数据库中插入 key  和value 并且设置过期时间 
     */  
    @SuppressWarnings("finally")  
    @Override  
    public boolean set(String key, V value, int exp) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        String jKey =key;  
        String jValue; 
        jValue=JSONSerializer.toJSON(value).toString();
        //操作是否成功  
        
        boolean isSucess =true;  
        /*if(StringUtils.isNotEmpty(jKey)){  
            LOG.info("key is empty");  
            return false;  
        } */ 
        try {  
            //获取客户端对象  
    
            jedis =redisClient.getResource();  
            //执行插入  
        	//System.out.println(jedis);
            jedis.setex(jKey, exp, jValue);  
            //System.out.println(jedis.get(jKey));
        } catch (JedisException e) {  
            System.out.println("获取失败");
            isSucess =false;  
            if(null !=jedis){  
                //释放jedis对象  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if(isSucess){  
                //返还连接池  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }
    public boolean zadd(String key, double score, String member) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        String jKey =key;  
        boolean isSucess =true;  
        try {  
            //获取客户端对象  
    
            jedis =redisClient.getResource();  
            //执行插入  
            jedis.zadd(jKey, score,member);  
        } catch (JedisException e) {  
            System.out.println("获取失败");
            isSucess =false;  
            if(null !=jedis){  
                //释放jedis对象  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if(isSucess){  
                //返还连接池  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }
    public Object get(String key) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        //JSONObject json = JSONObject.fromObject(key);
        String jKey=key;//json.toString();
        JSONObject jValue =null;  
        //key 不能为空  
       
        try {  
            //获取客户端对象  
            jedis =redisClient.getResource();  
            //执行查询  
            String value =  jedis.get(jKey);  
            //判断值是否非空  
            if("".equals(value)){  
                return null;  
            }else{  
            	jValue=(JSONObject)JSONObject.fromObject(value);
            }  
            //返还连接池  
            redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
              
            if(null !=jedis){  
                //释放jedis 对象  
                redisClient.brokenResource(jedis);  
            }  
        }  
        return jValue;  
    }  
 
    /** 
     * 设置哈希类型数据到redis 数据库 
     * @param cacheKey 可以看做一张表 
     * @param key   表字段 
     * @param value   
     * @return 
     */    
    @SuppressWarnings("finally")
	public boolean hset(String cacheKey, String key, V value) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        String jCacheKey=cacheKey;
        String jKey =key;  
        String jValue; 
        jValue=JSONSerializer.toJSON(value).toString();
        //操作是否成功  
        boolean isSucess =true;  
        if("".equals(jCacheKey)){  
        	System.out.println("cacheKey is empty");
            //LOG.info("cacheKey is empty");  
            return false;  
        }  
        try {  
            jedis =redisClient.getResource();  
            //执行插入哈希  
            jedis.hset(jCacheKey, jKey, jValue);  
        } catch (JedisException e) {  
            isSucess =false;  
            if(null !=jedis){  
                //释放jedis 对象  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if (isSucess) {  
                //返还连接池  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }  
    /** 
     * 获取哈希表数据类型的值 
     * @param cacheKey 
     * @param key 
     * @return 
     */  
    
    public Object hget(String cacheKey, String key) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        String jCacheKey=cacheKey;
        String jKey =key;  
        JSONObject jValue =null;  
        if("".equals(jCacheKey)){  
           
            return null;  
        }  
        try {  
            //获取客户端对象  
            jedis =redisClient.getResource();  
            //执行查询  
            String value =  jedis.hget(jCacheKey, jKey);  
            //判断值是否非空  
            if("".equals(jCacheKey)){             
                return null;  
            }  else{  
                jValue= (JSONObject)JSONObject.fromObject(value);
            }  
            //返还连接池  
            redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
            if(null !=jedis){  
                //释放jedis 对象  
                redisClient.brokenResource(jedis);  
            }  
        }  
        return jValue;  
    }  
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        Set<String> value=null;
        try {  
            //获取客户端对象  
            jedis =redisClient.getResource();  
            //执行查询  
            
            value =  jedis.zrevrangeByScore(key, max, min, offset, count);
            //判断值是否非空  
            
            //返还连接池  
            redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
            if(null !=jedis){  
                //释放jedis 对象  
                redisClient.brokenResource(jedis);  
            }  
        }  
        return value;  
    }  
	public boolean remove(String key) {  
        Jedis jedis =null;  
        //将key 和value  转换成 json 对象  
        String jKey =key;  
        //操作是否成功  
        boolean isSucess =true;  
        if("".equals(jKey)){  
            
            return false;  
        }  
        try {  
            jedis =redisClient.getResource();  
            //执行删除  
            jedis.del(jKey);  
        } catch (JedisException e) {  
            
            isSucess =false;  
            if(null !=jedis){  
                //释放jedis 对象  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if (isSucess) {  
                //返还连接池  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }  
}
