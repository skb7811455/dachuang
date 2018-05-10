package dao;

import model.User;

public interface IUserDAO {
	public boolean findLogin(User user) throws Exception ;
    public boolean Register(User user) throws Exception;
}
