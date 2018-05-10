package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DAOFactory;
import model.User;
import net.sf.json.JSONObject;
import service.Recommendation;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private Recommendation rec;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        
		
		
		String path = "index.html" ;     
        String userid = request.getParameter("id") ;
        String userpass = request.getParameter("password") ;
        response.setContentType("utf-8"); 
        response.setCharacterEncoding("utf-8"); 
        JSONObject jsonObject = new JSONObject();  
        HttpSession session = request.getSession(true);
        
        if(session.getAttribute("user")!=null){
        	jsonObject.put("code", "1");         //锟斤拷锟斤拷Json锟斤拷锟斤拷锟斤拷锟斤拷锟�
            jsonObject.put("message", "宸茬粡鐧诲綍");
        }else{
        
        	if(userid==null || "".equals(userid)){
        		jsonObject.put("code", "0");         //锟斤拷锟斤拷Json锟斤拷锟斤拷锟斤拷锟斤拷锟�
                jsonObject.put("message", "鐢ㄦ埛鍚嶄笉鑳戒负绌�");
        	}else if(userpass==null || "".equals(userpass)){
        		jsonObject.put("code", "0");         //锟斤拷锟斤拷Json锟斤拷锟斤拷锟斤拷锟斤拷锟�
                jsonObject.put("message", "瀵嗙爜涓嶈兘涓虹┖");
        	}else { 
        		User user = new User() ;
        		user.setUserid(userid) ;
        		user.setPassword(userpass) ;
        		try{
        			if(DAOFactory.getIUserDAOInstance().findLogin(user)){	
        				session.setAttribute("user", userid);
        				jsonObject.put("code", "1");         //锟斤拷锟斤拷Json锟斤拷锟斤拷锟斤拷锟斤拷锟�
                        jsonObject.put("message", "success!");
                        JSONObject data=new JSONObject();
                        data.put("id", userid);
                        jsonObject.put("data", data.toString());
                        this.rec=new Recommendation();
                        rec.init();
                        session.setAttribute("rec", this.rec);
        			} else {
                    	jsonObject.put("code", "0");         //锟斤拷锟斤拷Json锟斤拷锟斤拷锟斤拷锟斤拷锟�
                        jsonObject.put("message", "Failed");
                    }
               }catch(Exception e){
                e.printStackTrace() ;
               }
        	}
        }
        response.getWriter().write(jsonObject.toString());
        
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response) ;
    }
 

}
