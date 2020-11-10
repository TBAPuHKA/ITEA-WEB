package service;

import java.util.List;

import model.Product;
import model.User;

public interface UserDAO {
	public User getLogin(String login, String password);
	public void insertUser(User reg);
	public String hashPassword(String s);
	
}
