package dao;

public class DAOFactory {
	public static IUserDAO getIUserDAOInstance(){
        return new UserDAOProxy() ;
    }
	public static ISaleGoodDAO getISaleGoodDAOInstance(){
        return new SaleGoodDAOProxy() ;
    }
	

}
