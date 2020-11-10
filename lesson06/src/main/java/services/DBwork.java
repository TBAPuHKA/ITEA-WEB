 package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import auth.model.Auth;
import reg.model.Reg;


public class DBwork {
	private  final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String DB_CONNECT = "jdbc:mysql://localhost/test?" + "user=root&password=";
	private final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private final String INSERT_USER = "INSERT INTO `users` (`login`, `password`, `name`, `gender`, `address`, `comment`) VALUES (?,?,?,?,?,?)";

	public DBwork() {
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
	
	public Auth getName(String login, String password) {
		Auth auth = null;
		Connection con = getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_USER);){
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				auth = new Auth();
				auth.setName(rs.getString("NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return auth;
	}
	
	public void insertUser(Reg reg) {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			pst = conn.prepareStatement(INSERT_USER);
			pst.setString(1, reg.getLogin());
			pst.setString(2, reg.getPassword());
			pst.setString(3, reg.getName());
			pst.setString(4, reg.getGender());
			pst.setString(5, reg.getAddress());
			pst.setString(6, reg.getComment());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
