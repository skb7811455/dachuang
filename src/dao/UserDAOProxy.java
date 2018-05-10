package dao;

import model.User;
import net.sf.json.JSONObject;
import redis.RedisCacheStorage;
import redis.RedisCacheStorageImpl;
import redis.RedisClient;
import uitls.DatabaseConnection;
import uitls.JedisPoolConnection;

public class UserDAOProxy implements IUserDAO {
    private DatabaseConnection dbc = null ;
    private JedisPoolConnection jbc=null;
    private IUserDAO dao = null ;
    private static final String  cacheKey  ="user"; 
    private RedisCacheStorage <String,User>redisCache = null ;
    private RedisClient redisClient=null;
    
    public UserDAOProxy(){
        try{
            this.dbc = new DatabaseConnection() ;
            this.dao = new UserDAOImpl(dbc.getConnection()) ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        try{
            this.jbc = new JedisPoolConnection() ;
            this.redisCache = new RedisCacheStorageImpl<User>();
            this.redisClient=new RedisClient();
            this.redisClient.setPool(jbc.getConnection());
            this.redisCache.setRedisClient(redisClient);
        }catch(Exception e){
            e.printStackTrace() ;
        }      
    }
    public boolean findLogin(User user) throws Exception{
        boolean flag = false ;
        if(user==null) return false;
        
      
        try{
        	JSONObject j=(JSONObject)redisCache.hget(cacheKey, user.getUserid());
			User result=(User)JSONObject.toBean(j,User.class);
        	if(result!=null){
        		flag=true;
        		user.setName(result.getName());;
        		System.out.print(result.getUserid());
        	}else{
        		 System.out.print("数据库查询数据");
        	
        		 if(this.dao.findLogin(user)) redisCache.hset(cacheKey, user.getUserid(), user);
                 // 调用真实主题，完成操作
        	}
           
            
        }catch(Exception e){
            throw e ;
        }finally{
            this.dbc.close() ;
        }
        return flag ;
    }
    public boolean Register(User user) throws Exception{
        boolean flag = false ;
        try{
            flag = this.dao.Register(user) ;   // 
        }catch(Exception e){
            throw e ;
        }finally{
            this.dbc.close() ;
        }
        return flag ;
    }
} 

