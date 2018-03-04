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
        JSONObject jsonObject = new JSONObject();  //����Json����
        
        
        
        List<String> info = new ArrayList<String>() ;   // �ռ�����
        
        
        HttpSession session = req.getSession(true);
        
        if(session.getAttribute("user")!=null){
        	jsonObject.put("code", "1");         //����Json���������
            jsonObject.put("message", "�Ѿ���¼");
            System.out.println("�û��Ѿ���¼");
        }else{
        
        	if(userid==null || "".equals(userid)){
        		info.add("�û�id����Ϊ�գ�") ;
        		jsonObject.put("code", "0");         //����Json���������
                jsonObject.put("message", "�û�id����Ϊ��!");
        	}
        	if(userpass==null || "".equals(userpass)){
        		info.add("���벻��Ϊ�գ�") ;
        		jsonObject.put("code", "0");         //����Json���������
                jsonObject.put("message", "�û����벻��Ϊ��!");
        	}
        	if(info.size()==0){ // ����û�м�¼�κεĴ���
        		User user = new User() ;
        		user.setUserid(userid) ;
        		user.setPassword(userpass) ;
        		try{
        			if(DAOFactory.getIUserDAOInstance().findLogin(user)){	
        				session.setAttribute("user", userid);
        				jsonObject.put("code", "1");         //����Json���������
                        jsonObject.put("message", "success!");
                        
                        JSONObject data=new JSONObject();
                        data.put("id", userid);
                        jsonObject.put("data", data.toString());
                             //����Json���������
                        info.add("�û���½�ɹ�����ӭ" + user.getName() + "���٣�") ;
        			} else {
                        info.add("�û���½ʧ�ܣ�������û��������룡") ;
                    	jsonObject.put("code", "0");         //����Json���������
                        jsonObject.put("message", "������û��������룡");
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
