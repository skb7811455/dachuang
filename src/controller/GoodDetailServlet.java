package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.SaleGood;
import net.sf.json.JSONObject;
import service.Recommendation;
public class GoodDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Recommendation rec;
	 public GoodDetailServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("utf-8"); 
	     response.setCharacterEncoding("utf-8");
		 int id=Integer.parseInt(request.getParameter("id")) ;
		 System.out.println(id);
		 SaleGood good=new SaleGood();
		 JSONObject json=new JSONObject();
		 HttpSession session = request.getSession(true);
		 
		 rec=(Recommendation)session.getAttribute("rec");
		 if(rec!=null){
		 rec.showData();
		 rec.updateScore(id);
		}
		 try {
			json=DAOFactory.getISaleGoodDAOInstance().GoodDetail(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 response.getWriter().write(json.toString());
	 }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
     }
}
