package dao;

import java.sql.SQLException;
import java.util.List;

import model.SaleGood;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ISaleGoodDAO {
	public SaleGood getGoodByID(SaleGood good) throws Exception ;
	public boolean createSaleGood(SaleGood good) throws Exception ;
	public JSONObject extractJSONArray(int page,int type) throws SQLException;
	public JSONObject GoodDetail(int id) throws SQLException;
	public JSONObject Serach(String str,int page) throws SQLException;
	public SaleGood[] getGoods() throws Exception ;
}
