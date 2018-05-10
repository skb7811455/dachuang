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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String picture = request.getParameter("img") ;
		 String picture1 = request.getParameter("img1") ;
		 String picture2 = request.getParameter("img2") ;
		 String picture3 = request.getParameter("img3") ;
	     String goodname = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8") ;
	     String description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "UTF-8") ;
	     
	     String price = request.getParameter("price") ;
	     String qq=request.getParameter("qq");
	     String phone=request.getParameter("tel");
	     String userid = (String)request.getSession().getAttribute("user");
	     String bigsort = request.getParameter("sort") ;
	     String place = new String(request.getParameter("place").getBytes("ISO-8859-1"), "UTF-8") ;
	              
	     List<String> info = new ArrayList<String>() ;
	     
	     response.setContentType("utf-8"); 
	     response.setCharacterEncoding("utf-8"); 
	     JSONObject jsonObject = new JSONObject();  //����Json����
	     
	     
	     if(goodname==null||goodname.equals("")){
	    	 info.add("");
	     }
	     if(description==null||description.equals("")){
	    	 info.add("��Ʒ��������Ϊ��");
	     }
	     if(picture==null||picture.equals("")){
	    	 info.add("��Ʒ���治��Ϊ��");
	     }
	     if(price==null||price.equals("")){
	    	 info.add("��Ʒ�۸���Ϊ��");
	     }
	     if(place==null||place.equals("")){
	    	 info.add("���׵ص㲻��Ϊ��");
	     }
	     if(userid==null||userid.equals("")){
	    	 info.add("�����û�����Ϊ��");
	     }
	     if(bigsort==null||bigsort.equals("")){
	    	 info.add("�������Ͳ���Ϊ��");
	     }
	     System.out.println(info);
	     if(info.size()==0){
	    	 SaleGood good=new SaleGood();
	    	 good.setName(goodname);
	    	 good.setDescription(description);	 
	    	 good.setImg(picture);
	    	 good.setImg1(picture1);
	    	 good.setImg2(picture2);
	    	 good.setImg3(picture3);
	    	 good.setQq(qq);
	    	 good.setTelephone(phone);
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
	              jsonObject.put("code", "1");         //����Json���������
	 	          jsonObject.put("msg", "success");
	             }
	    	 }catch(Exception e){
	              e.printStackTrace() ;
	          }
	    	 
	     }else{
	    	 jsonObject.put("code", "0");         //����Json���������
	         jsonObject.put("msg", "��Ϣ������");
	     }
	     
	     response.getWriter().write(jsonObject.toString());
	     
    	 
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
