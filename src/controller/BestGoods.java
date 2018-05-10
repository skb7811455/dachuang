package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SaleGood;
import net.sf.json.JSONObject;
import service.Recommendation;

public class BestGoods extends HttpServlet{
	private Recommendation rec;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("utf-8"); 
	    response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		JSONObject object=new JSONObject();
		HttpSession session = request.getSession(true);			
		rec=(Recommendation)session.getAttribute("rec");
		if(null!=rec){
			SaleGood []goods=rec.recommend();
			json.put("code", "1");
			json.put("message","success");
			json.put("data", goods);
			response.getWriter().write(json.toString());
		}else{
			json.put("code", "0");
			json.put("message","false");
			
			response.getWriter().write(json.toString());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
    }
}
