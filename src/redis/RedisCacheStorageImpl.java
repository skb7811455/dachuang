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
            //��ȡ�ͻ��˶���  
          
            jedis =redisClient.getResource();  
            //ִ�в���  
        	//System.out.println(jedis);
            jedis.setex(key, EXPRIE_TIME, value+"");  
            //System.out.println(jedis.get(jKey));
        } catch (JedisException e) {  
            System.out.println("��ȡʧ��");
            isSucess =false;  
            if(null !=jedis){  
                //�ͷ�jedis����  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if(isSucess){  
                //�������ӳ�  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }    
	}
	public String getStr(String jKey){
		Jedis jedis=null;
		String value=null;
		try {  
            //��ȡ�ͻ��˶���  
            jedis =redisClient.getResource();  
            //ִ�в�ѯ  
            value =  jedis.get(jKey);  
            //�ж�ֵ�Ƿ�ǿ�  
            if("".equals(value)){  
                return null;  
            }else{  
            	  
            	
            }  
            redisClient.returnResource(jedis);
            //�������ӳ�  
           // redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
              
            if(null !=jedis){  
                //�ͷ�jedis ����  
                redisClient.brokenResource(jedis);  
            }  
        }  
		return value;
	}
    public void setRedisClient(RedisClient redisClient) {  
        this.redisClient = redisClient;  
    }  
    /** 
     * ��redis���ݿ��в��� key  ��value 
     */  
    @Override  
    public boolean set(String key, V value) {  
        //����Ĭ�Ϲ�ʱʱ��  
        return set(key, value, EXPRIE_TIME);  
    }  
    /** 
     * ��redis���ݿ��в��� key  ��value �������ù���ʱ�� 
     */  
    @SuppressWarnings("finally")  
    @Override  
    public boolean set(String key, V value, int exp) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        String jKey =key;  
        String jValue; 
        jValue=JSONSerializer.toJSON(value).toString();
        //�����Ƿ�ɹ�  
        
        boolean isSucess =true;  
        /*if(StringUtils.isNotEmpty(jKey)){  
            LOG.info("key is empty");  
            return false;  
        } */ 
        try {  
            //��ȡ�ͻ��˶���  
    
            jedis =redisClient.getResource();  
            //ִ�в���  
        	//System.out.println(jedis);
            jedis.setex(jKey, exp, jValue);  
            //System.out.println(jedis.get(jKey));
        } catch (JedisException e) {  
            System.out.println("��ȡʧ��");
            isSucess =false;  
            if(null !=jedis){  
                //�ͷ�jedis����  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if(isSucess){  
                //�������ӳ�  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }
    public boolean zadd(String key, double score, String member) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        String jKey =key;  
        boolean isSucess =true;  
        try {  
            //��ȡ�ͻ��˶���  
    
            jedis =redisClient.getResource();  
            //ִ�в���  
            jedis.zadd(jKey, score,member);  
        } catch (JedisException e) {  
            System.out.println("��ȡʧ��");
            isSucess =false;  
            if(null !=jedis){  
                //�ͷ�jedis����  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if(isSucess){  
                //�������ӳ�  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }
    public Object get(String key) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        //JSONObject json = JSONObject.fromObject(key);
        String jKey=key;//json.toString();
        JSONObject jValue =null;  
        //key ����Ϊ��  
       
        try {  
            //��ȡ�ͻ��˶���  
            jedis =redisClient.getResource();  
            //ִ�в�ѯ  
            String value =  jedis.get(jKey);  
            //�ж�ֵ�Ƿ�ǿ�  
            if("".equals(value)){  
                return null;  
            }else{  
            	jValue=(JSONObject)JSONObject.fromObject(value);
            }  
            //�������ӳ�  
            redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
              
            if(null !=jedis){  
                //�ͷ�jedis ����  
                redisClient.brokenResource(jedis);  
            }  
        }  
        return jValue;  
    }  
 
    /** 
     * ���ù�ϣ�������ݵ�redis ���ݿ� 
     * @param cacheKey ���Կ���һ�ű� 
     * @param key   ���ֶ� 
     * @param value   
     * @return 
     */    
    @SuppressWarnings("finally")
	public boolean hset(String cacheKey, String key, V value) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        String jCacheKey=cacheKey;
        String jKey =key;  
        String jValue; 
        jValue=JSONSerializer.toJSON(value).toString();
        //�����Ƿ�ɹ�  
        boolean isSucess =true;  
        if("".equals(jCacheKey)){  
        	System.out.println("cacheKey is empty");
            //LOG.info("cacheKey is empty");  
            return false;  
        }  
        try {  
            jedis =redisClient.getResource();  
            //ִ�в����ϣ  
            jedis.hset(jCacheKey, jKey, jValue);  
        } catch (JedisException e) {  
            isSucess =false;  
            if(null !=jedis){  
                //�ͷ�jedis ����  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if (isSucess) {  
                //�������ӳ�  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }  
    /** 
     * ��ȡ��ϣ���������͵�ֵ 
     * @param cacheKey 
     * @param key 
     * @return 
     */  
    
    public Object hget(String cacheKey, String key) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        String jCacheKey=cacheKey;
        String jKey =key;  
        JSONObject jValue =null;  
        if("".equals(jCacheKey)){  
           
            return null;  
        }  
        try {  
            //��ȡ�ͻ��˶���  
            jedis =redisClient.getResource();  
            //ִ�в�ѯ  
            String value =  jedis.hget(jCacheKey, jKey);  
            //�ж�ֵ�Ƿ�ǿ�  
            if("".equals(jCacheKey)){             
                return null;  
            }  else{  
                jValue= (JSONObject)JSONObject.fromObject(value);
            }  
            //�������ӳ�  
            redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
            if(null !=jedis){  
                //�ͷ�jedis ����  
                redisClient.brokenResource(jedis);  
            }  
        }  
        return jValue;  
    }  
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        Set<String> value=null;
        try {  
            //��ȡ�ͻ��˶���  
            jedis =redisClient.getResource();  
            //ִ�в�ѯ  
            
            value =  jedis.zrevrangeByScore(key, max, min, offset, count);
            //�ж�ֵ�Ƿ�ǿ�  
            
            //�������ӳ�  
            redisClient.returnResource(jedis);  
        } catch (JedisException e) {  
            if(null !=jedis){  
                //�ͷ�jedis ����  
                redisClient.brokenResource(jedis);  
            }  
        }  
        return value;  
    }  
	public boolean remove(String key) {  
        Jedis jedis =null;  
        //��key ��value  ת���� json ����  
        String jKey =key;  
        //�����Ƿ�ɹ�  
        boolean isSucess =true;  
        if("".equals(jKey)){  
            
            return false;  
        }  
        try {  
            jedis =redisClient.getResource();  
            //ִ��ɾ��  
            jedis.del(jKey);  
        } catch (JedisException e) {  
            
            isSucess =false;  
            if(null !=jedis){  
                //�ͷ�jedis ����  
                redisClient.brokenResource(jedis);  
            }  
            return false;  
        }finally{  
            if (isSucess) {  
                //�������ӳ�  
                redisClient.returnResource(jedis);  
            }  
            return true;  
        }  
    }  
}
