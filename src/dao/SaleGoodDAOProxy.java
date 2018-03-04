package dao;

import java.util.List;

import model.SaleGood;
import uitls.DatabaseConnection;

public class SaleGoodDAOProxy implements ISaleGoodDAO{
	private DatabaseConnection dbc = null ;
    private ISaleGoodDAO dao = null ;
	public SaleGoodDAOProxy(){
        try{
            this.dbc = new DatabaseConnection() ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        this.dao = new SaleGoodDAOImpl(dbc.getConnection()) ;
    }
	public SaleGood getGoodByID(SaleGood good) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSaleGood(SaleGood good) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.createSaleGood(good);
	}

	@Override
	public List<SaleGood> getGoodList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
