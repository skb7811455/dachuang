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

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        
		
		
		String path = "index.html" ;     
        String userid = req.getParameter("id") ;
        String userpass = req.getParameter("password") ;
        
        resp.setContentType("utf-8"); 
        resp.setCharacterEncoding("utf-8"); 
        JSONObject jsonObject = new JSONObject();  //创建Json对象
        
        
        
        List<String> info = new ArrayList<String>() ;   // 收集错误
        
        
        HttpSession session = req.getSession(true);
        
        if(session.getAttribute("user")!=null){
        	jsonObject.put("code", "1");         //设置Json对象的属性
            jsonObject.put("message", "已经登录");
            System.out.println("用户已经登录");
        }else{
        
        	if(userid==null || "".equals(userid)){
        		info.add("用户id不能为空！") ;
        		jsonObject.put("code", "0");         //设置Json对象的属性
                jsonObject.put("message", "用户id不能为空!");
        	}
        	if(userpass==null || "".equals(userpass)){
        		info.add("密码不能为空！") ;
        		jsonObject.put("code", "0");         //设置Json对象的属性
                jsonObject.put("message", "用户密码不能为空!");
        	}
        	if(info.size()==0){ // 里面没有记录任何的错误
        		User user = new User() ;
        		user.setUserid(userid) ;
        		user.setPassword(userpass) ;
        		try{
        			if(DAOFactory.getIUserDAOInstance().findLogin(user)){	
        				session.setAttribute("user", userid);
        				jsonObject.put("code", "1");         //设置Json对象的属性
                        jsonObject.put("message", "success!");
                        
                        JSONObject data=new JSONObject();
                        data.put("id", userid);
                        jsonObject.put("data", data.toString());
                             //设置Json对象的属性
                        info.add("用户登陆成功，欢迎" + user.getName() + "光临！") ;
        			} else {
                        info.add("用户登陆失败，错误的用户名和密码！") ;
                    	jsonObject.put("code", "0");         //设置Json对象的属性
                        jsonObject.put("message", "错误的用户名和密码！");
                    }
               }catch(Exception e){
                e.printStackTrace() ;
               }
        	}
        }
        System.out.println(info);
       
        req.setAttribute("info",info) ;
        resp.getWriter().write(jsonObject.toString());
        
    }
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        this.doGet(req,resp) ;
    }
 

}
