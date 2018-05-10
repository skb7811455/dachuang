package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

import dao.DAOFactory;
import model.Cos;
import model.SaleGood;
import net.sf.json.JSONObject;
import uitls.Heap;

public class Recommendation {   //电影item,列代表商品，行表示特征演员
	double[][] itemActor={{1,0,0,0,0},  //点击数在0~100
			           {1,0,0,0,0},  //点击数在100~200
			           {1,0,0,0,0},  //点击数大于200
	                   {0,1,0,1,1},  //价格在0-100
	                   {0,1,0,1,1},  //价格在100-500
	                   {0,1,0,1,1},  //价格在500-1000
	                   {0,1,0,1,1},  //价格在在1000-3000
	                   {0,1,0,1,1},  //价格在在3000以上
	                   {1,0,0,1,1},  //手机
	                   {1,0,0,1,1},  //电脑
	                   {0,0,1,0,0},   //书籍
	                   {0,1,1,0,0}};  //健身
  //int[] userClick={0,5,3,4};//用户点击表,列代表用户是否点击过
	int[] userClick={};	 
	int[] quanzhong={1,1,1,1,1,1,1,1,1,5,5,5};
	double[] userProfile={0,0,0,0,0,0,0,0,0,0,0,0}; //用户喜好度,列代表用户对特征演员的喜欢度
	//double[] cos={0,0,0,0,0};	
	Cos[] coses;
	SaleGood[] goods;
	Hashtable hash;
	Heap h=new Heap();
	public void init(){
		System.out.println("推荐模块启动");
		try {
			goods=DAOFactory.getISaleGoodDAOInstance().getGoods();
			int length=goods.length;
			int proLen=userProfile.length;
			System.out.println("总条数"+length);
			itemActor=new double[proLen][length];
			hash=new Hashtable();
			userClick=new int[length];
			coses=new Cos[length];
			for(int j=0;j<length;j++){
				userClick[j]=0;
				int click=goods[j].getClickTimes();
				int price=goods[j].getPrice();
				int type=goods[j].getType();
				coses[j]=new Cos();
				coses[j].setId(goods[j].getId());
				hash.put(goods[j].getId(), j);
					if(click<100){
						itemActor[0][j]=1;
					}
					if(click>100&&click<200){
						itemActor[1][j]=1;
					}
					if(click>200){
						itemActor[2][j]=1;
					}
					if(price<100){
						itemActor[3][j]=1;
					}
					if(price>100&&price<500){
						itemActor[4][j]=1;
					}
					if(price>500&&price<1000){
						itemActor[5][j]=1;
					}
					if(price>1000&&price<3000){
						itemActor[6][j]=1;
					}
					if(price>3000){
						itemActor[7][j]=1;
					}
					if(type==2){
						itemActor[8][j]=1;
					}
					if(type==3){
						itemActor[9][j]=1;
					}
					if(type==7){
						itemActor[10][j]=1;
					}
					if(type==8){
						itemActor[11][j]=1;
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public void showData(){
		for(int i=0;i<userProfile.length;i++){
		   System.out.println(userProfile[i]);  
		}
		
	}
	public void updateScore(int id){ 
	    if(null==hash.get(id)) return;
		int index=Integer.parseInt(hash.get(id).toString());
		//if(userClick[index]<5){
			userClick[index]=1;
			learn();
		//}
	}
	public void learn(){
		System.out.println();
		int n=0;
	    int score=0;
	    int avgScore=0;
		for(int i=0;i<userClick.length;i++){
        	if(userClick[i]!=0){
        		n++;
        		score=score+userClick[i];
        	}   	
        }
		if(n!=0){
			avgScore=score/n;
		}
        for(int i=0;i<userProfile.length;i++){
        	double totalX=0;
        	double avg=0;
        	double m=0;
        	for(int j=0;j<itemActor[i].length;j++){
        		if((itemActor[i][j]==1)&&userClick[j]!=0){
        			totalX+=1;
        		}
        	}       	
        	avg=totalX/n;
        	userProfile[i]=avg;   	 
        	
        	/*for(int j=0;j<itemActor[i].length;j++){
    		if((itemActor[i][j]!=0)&&userClick[j]!=0){
    			totalX+=userClick[j]-avgScore;
    			m=m+1;
    		}
    		}   */    
        	//if(m!=0){
    	 	//userProfile[i]=totalX/m;   	 
        	//} 
        } 
        for(int i=0;i<coses.length;i++){
        	double sum=0;
        	double usum=0;
        	double isum=0;
        	double sq=0;
        	for(int j=0;j<userProfile.length;j++){
        		sum=sum+userProfile[j]*itemActor[j][i];
        		usum=usum+userProfile[j]*userProfile[j];
        		isum=isum+itemActor[j][i]*itemActor[j][i];
        		sq=Math.sqrt(usum)*Math.sqrt(isum);	
        	}
        	if(sq!=0){
    			coses[i].setValue(sum/sq);
    		}
    		System.out.println("序号"+i+" cos:"+coses[i].getValue());
       }
      // showData();
	}
	public SaleGood[] recommend(){
	   int k=4;
	   Cos []c=new Cos[coses.length+1];
	   for(int i=1;i<c.length;i++){
		   c[i]=new Cos();
		   c[i].setValue(coses[i-1].getValue());
		   c[i].setId(coses[i-1].getId());
	   }
	   h.setCos(c);
	   h.createHeap();
	   int r[]=h.topK(k);
	   SaleGood []bestGoods=new SaleGood[k];
	   for(int i=0;i<k;i++){
		   if(null!=hash.get(r[i])){
			   int index=Integer.parseInt(hash.get(r[i]).toString());
			   bestGoods[i]=goods[index];
			   System.out.println(bestGoods[i].getName()+" cos:"+coses[index].getValue());
		   }
	   }
	   return bestGoods;
	}
}
