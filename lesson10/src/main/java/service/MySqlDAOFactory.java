package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory{

	private final String DB_CONNECT = "jdbc:mysql://localhost/test?user=root&password=";
	private final String DB_DRIVER = "com.mysql.jdbc.Driver";		

	public MySqlDAOFactory() {
		try {
			Class.forName(DB_DRIVER).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_CONNECT);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	@Override
	public UserDAO getUserDAO() {
		return new MySqlUserDAO(this);
	}

	@Override
	public ProductDAO getProductDAO() {
		return new MySqlProductDAO(this);
	}
	


}
