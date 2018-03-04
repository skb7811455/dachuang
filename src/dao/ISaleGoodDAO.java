package dao;

import java.util.List;

import model.SaleGood;

public interface ISaleGoodDAO {
	public SaleGood getGoodByID(SaleGood good) throws Exception ;
	public boolean createSaleGood(SaleGood good) throws Exception ;
	public List<SaleGood> getGoodList() throws Exception ;
}
