package redis;

import java.util.List;
import java.util.Set;

import model.User;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import uitls.JedisPoolConnection;



public class RedisJava {
	private RedisCacheStorageImpl<User> storageCache;  
	public void sortedItem(){
		
	}
	public static void main(String[] args) {
        //连接本地的 Redis 服务
	
		JedisPool jedisPool;
		/*
		try {
			jedisPool = new JedisPoolConnection().getConnection();
			
		RedisClient redisClient=new RedisClient();
		redisClient.setPool(jedisPool);
		RedisCacheStorageImpl<User> storageCache=new RedisCacheStorageImpl<User>();
		storageCache.setRedisClient(redisClient);
		User users[]=new User[5];
		
		Set<String> sets1=storageCache.zrevrangeByScore("topicId", "50", "0", 0, 5);
		if(!sets1.isEmpty()){
			for(String str:sets1){
				JSONObject j=(JSONObject)storageCache.hget("Comment_Key", str);
				User u=(User)JSONObject.toBean(j,User.class);
				System.out.println("redis数据"+u.getName());
	        }
		}else{
			for  ( int  i =  0 ; i<5;i++) {    
				users[i]=new User();
				users[i].setName("shukunbo"+i);
				System.out.println("数据库数据"+users[i].getName());
			    storageCache.zadd("topicId", i,users[i].getName());
			    storageCache.hset("Comment_Key","shukunbo"+i,users[i]); 
	        } 
		}
		
		
		User users1[]=new User[5];
		Set<String> sets2=storageCache.zrevrangeByScore("topicId", "10", "0", 5, 5);
		if(!sets2.isEmpty()){
			for(String str:sets2){
				JSONObject j=(JSONObject)storageCache.hget("Comment_Key", str);
				User u=(User)JSONObject.toBean(j,User.class);
				System.out.println("redis数据"+u.getName());
	        }
		}else{
			for  ( int  i =  5 ; i<10;i++) {    
				users1[i-5]=new User();  
				users1[i-5].setName("shukunbo"+i);
				System.out.println("数据库数据"+users1[i-5].getName());
				storageCache.zadd("topicId", i,users1[i-5].getName());
				storageCache.hset("Comment_Key","shukunbo"+i,users1[i-5]); 
	        } 
		}
		User users2[]=new User[2];
		for  ( int  i =  10 ; i<12;i++) {    
			users2[i-10]=new User();
			users2[i-10].setName("shukunbo"+i);
			System.out.println("数据库数据"+users2[i-10].getName());
		    storageCache.zadd("topicId", i,users2[i-10].getName());
		    storageCache.hset("Comment_Key","shukunbo"+i,users2[i-10]); 
        } 
		
        storageCache.set("bobo", users[0]);
        storageCache.remove("bobo");
        Object b=storageCache.get("bobo");
        System.out.println(b.toString());
        for  ( int  i =  0 ; i <  20 ; i++) {    
            // 初始化CommentId索引 SortSet  
        	storageCache.zadd("topicId", i, "commentId"+i);  
            // 初始化Comment数据 Hash  
        	storageCache.hset("Comment_Key","commentId"+i,users[i]);  
        } 
        
        
        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
}
