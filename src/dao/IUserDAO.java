package dao;

import model.User;

public interface IUserDAO {
	public boolean findLogin(User user) throws Exception ;

}
