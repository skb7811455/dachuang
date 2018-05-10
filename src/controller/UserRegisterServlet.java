package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DAOFactory;
import dao.UserDAOImpl;
import model.User;
import net.sf.json.JSONObject;

public class UserRegisterServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setCharacterEncoding("UTF-8");
	    String id=request.getParameter("id");
	    String password=request.getParameter("password");
	    String confirmPassword=request.getParameter("confirmPassword");
	    
	    JSONObject jsonObject=new JSONObject();
        
	    HttpSession session=request.getSession(true);
	    
	    User user = new User();
	    user.setUserid(id);
	    user.setPassword(password);
	    try {
	    	if(password.equals(confirmPassword)) {
	    		if(!(DAOFactory.getIUserDAOInstance().Register(user))) {
	    			session.setAttribute("user", id);
			    	jsonObject.put("code","1");
			    	jsonObject.put("massage","Success");
	    		}else {
	    			jsonObject.put("code", "0"); 
	                jsonObject.put("message", "用户名已存在");
	    		}
	    	}else {
	    		jsonObject.put("code", "0"); 
                jsonObject.put("message", "密码不一致");
	    	}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    response.getWriter().write(jsonObject.toString());
	}
	 public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	        this.doGet(request,response) ;
	    }
	 

}
