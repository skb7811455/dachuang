package uitls;

import java.sql.Connection;
import java.sql.DriverManager;

import redis.RedisClient;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolConnection {
	private static final String URL="127.0.0.1";
	private static final int PORT=6379;
	private static final int MAXACTIVE=100;
	private static final long MAXWAIT=15000;
	private static final int MAXIDLE=25;
	private static final boolean TESTONBORROW=false;
	private static final boolean TESTONRETURN=false;
	private JedisPool jedisPool=null;
	
	public JedisPoolConnection() throws Exception{
        
        JedisPoolConfig config=new JedisPoolConfig();
        
        config.setMaxIdle(MAXIDLE); 
        config.setMaxTotal(MAXACTIVE);
	    config.setMaxWaitMillis(MAXWAIT);
		config.setTestOnBorrow(TESTONBORROW);
		this.jedisPool = new JedisPool(config, URL, PORT);
			
    }
	public JedisPool getConnection(){
        return this.jedisPool ;
    }
    public void close() throws Exception{
        if(this.jedisPool != null){
            try{
                this.jedisPool.close() ;
            }catch(Exception e){
                throw e ;
            }
        }
    }
}

