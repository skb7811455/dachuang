 package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.SaleGood;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import uitls.DatabaseConnection;

public class SaleGoodDAOImpl implements ISaleGoodDAO {
	private Connection conn = null ;
    private PreparedStatement pstmt = null ;
    private PreparedStatement stmt = null ;
    private PreparedStatement st = null ;
    private PreparedStatement mt = null ;
    public SaleGoodDAOImpl(Connection conn){
        this.conn = conn ;
    }
	@Override
	public SaleGood getGoodByID(SaleGood good) throws Exception {
		return null;
	}
	@Override
	public boolean createSaleGood(SaleGood good) throws Exception {
		
        String sql = "INSERT INTO good(name,description,img,qq,phone,price,userid,type,time,place,clicktimes,img1,img2,img3)"
        		+"VALUES(?,?,?,?,?,?,?,?,CURRENT_DATE(),?,?,?,?,?)" ;
        
        this.pstmt = this.conn.prepareStatement(sql) ;
        this.pstmt.setString(1,good.getName()) ;
        this.pstmt.setString(2,good.getDescription()) ;
        this.pstmt.setString(3,good.getImg()) ;
        this.pstmt.setString(11, null);
        this.pstmt.setString(12, null);
        this.pstmt.setString(13, null);
        this.pstmt.setString(4, good.getQq());
        this.pstmt.setString(5, good.getTelephone());
        this.pstmt.setInt(6,good.getPrice()) ;
        this.pstmt.setString(7,good.getUserID());
        this.pstmt.setInt(8,good.getType()) ;   
        this.pstmt.setString(9,good.getPlace()) ;
        this.pstmt.setInt(10, 10);
                
        if(!this.pstmt.execute()){
        	int ret_id=0;
        	ResultSet rs = pstmt.getGeneratedKeys(); 
        	if (rs.next()){
        		ret_id = rs.getInt(1);
        		System.out.println("自增id:"+ret_id);
        		Date date=new Date();
        		good.setId(ret_id);
        		good.setTime(date.getTime());
        	}
        	this.pstmt.close() ;
        	this.conn.close();
        	return true;
        }else{
        	this.pstmt.close() ;
        	this.conn.close();
        	return false;
        }
	}
	@Override
	public JSONObject extractJSONArray(int page, int type) throws SQLException {
		// TODO Auto-generated method stub
		int count=0;//总页数,每页6条记录
		int num=0, pagesize=10,startindex;
		JSONArray data = new JSONArray(); 
		JSONObject object=new JSONObject();
		String sql = "SELECT * FROM Good  where type=? order by time desc " ;
		String sql1 = "SELECT * FROM Good order by time desc " ;
        this.pstmt = this.conn.prepareStatement(sql);
        this.mt=this.conn.prepareStatement(sql);
		this.stmt=this.conn.prepareStatement(sql1);
		this.st=this.conn.prepareStatement(sql1);
        this.pstmt.setInt(1, type);
        this.mt.setInt(1, type);
        ResultSet rs = this.pstmt.executeQuery() ;
        ResultSet rs3= this.mt.executeQuery() ;
        ResultSet rs1 = this.stmt.executeQuery() ;
        ResultSet rs2= this.st.executeQuery() ;
        if(type==0) {
        	while(rs2.next()) {
            	count++;
            }
        }else {
        	while(rs3.next()) {
        		count++;
        	}
        }
        
        if(count%6==0&&count>6) {
        	count=count/6;
        }else {
        	count=(count/10)+1;
        }
        startindex=(page-1)*pagesize;
        if(type==0) {
        	while(rs1.next()) {
        		if(pagesize>0&&num>=startindex) {
        			JSONObject data1=new JSONObject();
            		object.put("code", "1");
            		object.put("message","success");
            		data1.put("id", rs1.getInt("id"));
            		data1.put("name", rs1.getString("name"));
            		data1.put("price",rs1.getInt("price"));
            		data1.put("type", rs1.getInt("type"));
            		data1.put("img",rs1.getString("img"));
            		data1.put("clickTimes", rs1.getInt("clickTimes"));
            		data.add(data1);
            		object.put("data", data);
            		pagesize--;
        		}
        		num++;
        	}
        }else {
            while(rs.next()) {
            		if(pagesize>0&&num>=startindex) {
        			JSONObject data1=new JSONObject();
            		object.put("code", "1");
            		object.put("message","success");
            		data1.put("id", rs.getInt("id"));
            		data1.put("name", rs.getString("name"));
            		data1.put("price",rs.getInt("price"));
            		data1.put("type", rs.getInt("type"));
            		data1.put("img",rs.getString("img"));
            		data1.put("clickTimes", rs.getInt("clickTimes"));
            		data.add(data1);
            		object.put("data", data);
            		pagesize--;
        		      }
            	num++;
            	}
        }
        object.put("page", count);
        this.conn.close();
		return object;
	}
	
	public JSONObject GoodDetail(int id) throws SQLException {

		String sql = "SELECT * FROM Good WHERE id=?" ;
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		JSONObject object=new JSONObject();
		ResultSet rs = this.pstmt.executeQuery() ;
		while(rs.next()) {
			JSONObject data=new JSONObject();
			object.put("code", "1");
			object.put("message", "success");
			data.put("id", rs.getInt("id"));
			data.put("name", rs.getString("name"));
			data.put("price", rs.getInt("price"));
			data.put("description", rs.getString("description"));
			data.put("userid", rs.getString("userid"));
			data.put("place", rs.getString("place"));
			data.put("telephone", rs.getString("phone"));
			data.put("qq", rs.getString("qq"));
			data.put("type", rs.getInt("type"));
			data.put("img", rs.getString("img"));
			data.put("img1", rs.getString("img1"));
			data.put("img2", rs.getString("img2"));
			data.put("img3", rs.getString("img3"));
			data.put("time", rs.getDate("time").toString());
			object.put("data", data);
		}
		return object;
		
	}
	@Override
	public JSONObject Serach(String str,int page) throws SQLException {
		// TODO Auto-generated method stub
		int count=0;//总页数,每页6条记录
		int num=0, pagesize=6,startindex;
		JSONArray data = new JSONArray(); 
		JSONObject object=new JSONObject();
		String sql="select * form Good where name '%str%'";
		this.pstmt = this.conn.prepareStatement(sql);
		this.stmt=this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery() ;
		ResultSet rs1 = this.pstmt.executeQuery() ;
		while(rs1.next()) {
			count++;
		}
		if(count%6==0&&count>6) {
	        	count=count/6;
	    }else {
	        	count=(count/6)+1;
	    }
	    startindex=(page-1)*pagesize;
	    while(rs.next()) {
    		if(pagesize>0&&num>=startindex) {
    			JSONObject data1=new JSONObject();
        		object.put("code", "1");
        		object.put("message","success");
        		data1.put("id", rs1.getInt("id"));
        		data1.put("name", rs1.getString("name"));
        		data1.put("price",rs1.getInt("price"));
        		data1.put("type", rs1.getInt("type"));
        		data1.put("img",rs1.getString("img"));
        		data1.put("clickTimes", rs1.getInt("clickTimes"));
        		data.add(data1);
        		object.put("data", data);
        		pagesize--;
    		}
    		num++;
    	}
	    object.put("page", count);
		return object;
	}
	@Override
	public SaleGood[] getGoods() throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		int i=0;
		String sql = "SELECT id,name,price,type,img,clickTimes FROM Good" ;
		this.pstmt = this.conn.prepareStatement(sql);
		this.stmt=this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery() ;
		ResultSet rs1 = this.stmt.executeQuery() ;
		while(rs1.next()) {
			count++;
		}
		SaleGood[] goods=new SaleGood[count];
		while(rs.next()) {
			goods[i]=new SaleGood();
			goods[i].setId(rs.getInt("id"));
			goods[i].setName(rs.getString("name"));
			goods[i].setPrice(rs.getInt("price"));
			goods[i].setType(rs.getInt("type"));
			goods[i].setImg(rs.getString("img"));
			goods[i].setClickTimes(rs.getInt("clickTimes"));
			i++;				
		}
		
		return goods;
	}

}
