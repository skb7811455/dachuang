package dao;

import java.sql.* ;
import model.User;
import net.sf.json.JSONObject;
import uitls.DatabaseConnection;
public class UserDAOImpl implements IUserDAO {
    private Connection conn = null ;
    MD5 md5=new MD5();
    private  PreparedStatement pstmt = null ;
    public UserDAOImpl(Connection conn){
        this.conn = conn ;
    }
    public boolean findLogin(User user) throws Exception{
        boolean flag = false ;
        String sql = "SELECT * FROM t_user WHERE UserID=? AND PassWord=?" ;
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,user.getUserid()) ;
        this.pstmt.setString(2,md5.MD(user.getPassword())) ;
        ResultSet rs = this.pstmt.executeQuery() ;
        if(rs.next()){
            user.setName(rs.getString(1)) ; // ȡ��һ���û�����ʵ����
            flag = true ;
        }
        this.pstmt.close() ;
        this.conn.close();
        return flag ;
    }
    public boolean Register(User user) throws Exception{
    	boolean flag = false;
    	String sql = "insert into t_user values (?,?)";
        PreparedStatement pstmt = null;
        PreparedStatement stmt = null;
        String sql_t="SELECT * FROM t_user WHERE UserID=?";
        this.pstmt =this.conn.prepareStatement(sql_t);
        this.pstmt.setString(1,user.getUserid()) ;
        ResultSet rs = this.pstmt.executeQuery() ;
        if(rs.next()){
            user.setName(rs.getString(1)) ;
            flag = true ;
        }
        JSONObject jsonObject = new JSONObject();
        if(flag==true) {
        	return flag;
        }else {
        	 try {
        		 stmt = (PreparedStatement) this.conn.prepareStatement(sql);
                 stmt.setString(1,user.getUserid());
                 stmt.setString(2,md5.MD(user.getPassword()));
                 stmt.execute();
                 stmt.close();
            }catch(Exception e){
                e.printStackTrace() ;
            }
        }
        this.conn.close();
    	return flag;
    }
    
}

