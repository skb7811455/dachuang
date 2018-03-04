package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import model.SaleGood;
import net.sf.json.JSONObject;
import service.UpLoadService;
import uitls.ImgBase64;

/**
 * Servlet implementation class ReleaseSaleServlet
 */
@WebServlet("/ReleaseSaleServlet")
public class ReleaseSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseSaleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
	     String goodname = req.getParameter("name") ;
	     String description = req.getParameter("description") ;
	     String picture = req.getParameter("img") ;
	     String status = "9";
	     String sprice = "10";
	     String price = req.getParameter("price") ;
	     String userid = (String)req.getSession().getAttribute("user");
	     String bigsort = req.getParameter("sort") ;
	     String place = req.getParameter("place") ;
	              
	     System.out.println(picture);
	     List<String> info = new ArrayList<String>() ;
	     
	     resp.setContentType("utf-8"); 
	     resp.setCharacterEncoding("utf-8"); 
	     JSONObject jsonObject = new JSONObject();  //创建Json对象
	     
	     
	     if(goodname==null||goodname.equals("")){
	    	 info.add("商品名不能为空");
	     }
	     if(description==null||description.equals("")){
	    	 info.add("商品描述不能为空");
	     }
	     if(picture==null||picture.equals("")){
	    	 info.add("商品封面不能为空");
	     }
	     if(status==null||status.equals("")){
	    	 info.add("商品状态不能为空");
	     }
	     if(price==null||price.equals("")){
	    	 info.add("商品价格不能为空");
	     }
	     if(place==null||place.equals("")){
	    	 info.add("交易地点不能为空");
	     }
	     if(userid==null||userid.equals("")){
	    	 info.add("所属用户不能为空");
	     }
	     if(bigsort==null||bigsort.equals("")){
	    	 info.add("所属类型不能为空");
	     }
	     System.out.println(info);
	     if(info.size()==0){
	    	 SaleGood good=new SaleGood();
	    	 good.setName(goodname);
	    	 good.setDescription(description);	 
	    	 good.setImg(picture);
	    	 good.setStatus(status);
	    	 good.setSprice(Integer.parseInt(sprice));
	    	 good.setPrice(Integer.parseInt(price));
	    	 good.setUserID(userid);
	    	 good.setType(Integer.parseInt(bigsort));
	    	 good.setPlace(place);
	    	 
	    	// String path=req.getSession().getServletContext().getRealPath("/");
	    	 String path="C://tupian/";
	    	 UpLoadService upload=new UpLoadService();
	    	 upload.saveImg(good, path);
	    	 
	    	 try{
	    		 
	             if(DAOFactory.getISaleGoodDAOInstance().createSaleGood(good)){
	              //JSONObject data=JSONObject.fromObject(good);
                  //jsonObject.put("data", data.toString());
	              jsonObject.put("code", "1");         //设置Json对象的属性
	 	          jsonObject.put("msg", "success");
	             }
	    	 }catch(Exception e){
	              e.printStackTrace() ;
	          }
	    	 
	     }else{
	    	 jsonObject.put("code", "0");         //设置Json对象的属性
	         jsonObject.put("msg", "信息不完整");
	     }
	     
	     resp.getWriter().write(jsonObject.toString());
	     
    	 
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
