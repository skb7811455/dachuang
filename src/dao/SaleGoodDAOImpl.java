package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import model.SaleGood;
import uitls.DatabaseConnection;

public class SaleGoodDAOImpl implements ISaleGoodDAO {
	private Connection conn = null ;
    private PreparedStatement pstmt = null ;
    public SaleGoodDAOImpl(Connection conn){
        this.conn = conn ;
    }
	@Override
	public SaleGood getGoodByID(SaleGood good) throws Exception {
		return null;
	}
	@Override
	public boolean createSaleGood(SaleGood good) throws Exception {
		
        String sql = "INSERT INTO goods(GoodsName,Description,Picture,Status,SPrice,Price,User,BigSort,Time,Place)"
        		+"VALUES(?,?,?,?,?,?,?,?,CURRENT_DATE(),?)" ;
        
        this.pstmt = this.conn.prepareStatement(sql) ;
        this.pstmt.setString(1,good.getName()) ;
        this.pstmt.setString(2,good.getDescription()) ;
        this.pstmt.setString(3,good.getImg()) ;
        this.pstmt.setString(4,good.getStatus()) ;
        this.pstmt.setInt(5,good.getSprice());
        this.pstmt.setInt(6,good.getPrice()) ;
        this.pstmt.setString(7,good.getUserID());
        this.pstmt.setInt(8,good.getType()) ;   
        this.pstmt.setString(9,good.getPlace()) ;
                
        if(this.pstmt.execute()){
        	this.pstmt.close() ;
        	return true;
        }else{
        	this.pstmt.close() ;
        	return false;
        }
        
        		
	}
	@Override
	public List<SaleGood> getGoodList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
