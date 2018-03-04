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
   	 	good.setImg(relPath);
   	 	return uploadPath;
	}
}
