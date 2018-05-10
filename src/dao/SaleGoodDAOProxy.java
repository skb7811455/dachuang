package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.SaleGood;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.RedisCacheStorage;
import redis.RedisCacheStorageImpl;
import redis.RedisClient;
import uitls.DatabaseConnection;
import uitls.JedisPoolConnection;

public class SaleGoodDAOProxy implements ISaleGoodDAO{
	private DatabaseConnection dbc = null ;
    private ISaleGoodDAO dao = null ;
    private JedisPoolConnection jbc=null;
    private static final String  cacheKey  ="goodID"; 
    private static final String  hashKey  ="good"; 
    private RedisCacheStorage <String,SaleGood>redisCache = null ;
    private RedisClient redisClient=null;
	public SaleGoodDAOProxy(){
        try{
            this.dbc = new DatabaseConnection() ;
            this.dao = new SaleGoodDAOImpl(dbc.getConnection()) ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        try{
            this.jbc = new JedisPoolConnection() ;
            this.redisCache = new RedisCacheStorageImpl<SaleGood>();
            this.redisClient=new RedisClient();
            this.redisClient.setPool(jbc.getConnection());
            this.redisCache.setRedisClient(redisClient);
        }catch(Exception e){
            e.printStackTrace() ;
        }          
    }
	public SaleGood[] getGoods() throws Exception {
		return this.dao.getGoods();
	}
	public SaleGood getGoodByID(SaleGood good) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSaleGood(SaleGood good) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		flag=this.dao.createSaleGood(good);
		if(flag){
			this.redisCache.zadd(cacheKey, good.getPrice(), good.getId()+"");	
			this.redisCache.hset(hashKey, good.getId()+"", good);
			redisCache.set("click"+good.getId(), 1);
		}
		return flag;		
	}


	@Override
	public JSONObject extractJSONArray(int page, int type) throws SQLException {
		// TODO Auto-generated method stub
		return this.dao.extractJSONArray(page, type);
	}
	@Override
	public JSONObject GoodDetail(int id) throws SQLException {
		// TODO Auto-generated method stub;
		int count=1;
		JSONObject object=new JSONObject();
		JSONObject j=(JSONObject)redisCache.hget(hashKey,id+"");
		SaleGood result=(SaleGood)JSONObject.toBean(j,SaleGood.class);
		
		String clickString=redisCache.getStr("click"+id);
		System.out.println("点击数"+clickString);
		if(null!=clickString&&null!=result){
			count=Integer.parseInt(clickString);
			count++;
			result.setClickTimes(count);
		}
		redisCache.set("click"+id, count);
		
		if(result!=null){
			object.put("code", "1");
			object.put("message", "success");
			object.put("data", j);
			System.out.println("redis中的缓存商品");
			System.out.println(j.toString());
			System.out.println("点击数"+count);
			return object;
		}else{
			return this.dao.GoodDetail(id);
		}
		
	}
	@Override
	public JSONObject Serach(String str,int page) throws SQLException {
		// TODO Auto-generated method stub
		return this.dao.Serach(str,page);
	}

}
