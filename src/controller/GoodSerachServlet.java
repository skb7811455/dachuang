package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import net.sf.json.JSONObject;

public class GoodSerachServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String str=request.getParameter("maincode");
		int page=Integer.parseInt(request.getParameter("page"));
        JSONObject json = new JSONObject();
        
	    HttpSession session=request.getSession(true);
		
	    try {
			json=DAOFactory.getISaleGoodDAOInstance().Serach(str,page);
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
