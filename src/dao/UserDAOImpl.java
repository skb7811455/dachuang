package dao;

import java.sql.* ;

import model.User;
public class UserDAOImpl implements IUserDAO {
    private Connection conn = null ;
    private PreparedStatement pstmt = null ;
    public UserDAOImpl(Connection conn){
        this.conn = conn ;
    }
    public boolean findLogin(User user) throws Exception{
        boolean flag = false ;
        String sql = "SELECT UserName FROM user WHERE UserID=? AND PassWord=?" ;
        this.pstmt = this.conn.prepareStatement(sql) ;
        this.pstmt.setString(1,user.getUserid()) ;
        this.pstmt.setString(2,user.getPassword()) ;
        ResultSet rs = this.pstmt.executeQuery() ;
        if(rs.next()){
            user.setName(rs.getString(1)) ; // ȡ��һ���û�����ʵ����
            flag = true ;
        }
        this.pstmt.close() ;
        return flag ;
    }
}

