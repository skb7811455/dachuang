package service;

import model.SaleGood;
import model.User;
import uitls.ImgBase64;

public class UpLoadService {
	public String saveImg(SaleGood good,String path){
		long time=System.currentTimeMillis();
		String relPath="/upload/Image/"+time+".jpg";
   	 	String uploadPath =path+time+".jpg";
   	 	ImgBase64.generateImage(good.getImg(), uploadPath);
   	    ImgBase64.generateImage(good.getImg1(), uploadPath);
   	    ImgBase64.generateImage(good.getImg2(), uploadPath);
   	    ImgBase64.generateImage(good.getImg3(), uploadPath);
   	 	good.setImg(relPath);
   	 	good.setImg1(relPath);
   	 	good.setImg2(relPath);
   	 	good.setImg3(relPath);
   	 	return uploadPath;
	}
}
