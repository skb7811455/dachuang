package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.SaleGood;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.Recommendation;

public class GoodListServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setCharacterEncoding("UTF-8");
		String type1=new String(request.getParameter("type"));
		String page1=new String(request.getParameter("page"));
		JSONObject json = new JSONObject();
	    HttpSession session=request.getSession(true);
        
	    int type=Integer.parseInt(type1);
	    int page=Integer.parseInt(page1);
	    
	    try {
			DAOFactory.getISaleGoodDAOInstance().getGoods();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			json=DAOFactory.getISaleGoodDAOInstance().extractJSONArray(page, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    response.getWriter().write(json.toString());  
	}
	 public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	       doGet(request,response) ;
	}
	 
}
