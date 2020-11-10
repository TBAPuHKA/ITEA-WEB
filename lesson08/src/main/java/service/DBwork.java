 package service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;


public class DBwork {
	private final String DB_CONNECT = "jdbc:mysql://localhost/test?user=root&password=";
	private final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private  final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String INSERT_USER = "INSERT INTO `users` (`login`, `password`, `name`, `gender`, `address`, `comment`) VALUES (?,?,?,?,?,?)";
	private Connection conn;
	private final static String GET_PRODUCTS = "SELECT * FROM PRODUCT";
	private final String SALT = "askl1$zQ%11w";
	
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
		try {
			conn = DriverManager.getConnection(DB_CONNECT);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}
	
	
	public User getLogin(String login, String password) {
		User auth = null;
		Connection con = getConnection();
		try(PreparedStatement ps = con.prepareStatement(SELECT_USER);){
			ps.setString(1, login);
			ps.setString(2, hashPassword(password+SALT));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				auth = new User();
				auth.setName(rs.getString("NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return auth;
	}
	
	public void insertUser(User reg) {
		PreparedStatement pst = null;
		Connection conn = getConnection();
		
		try {
			pst = conn.prepareStatement(INSERT_USER);
			pst.setString(1, reg.getLogin());
			pst.setString(2, hashPassword(reg.getPassword()+SALT));
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
	
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		getConnection();

		try (Statement ps = conn.createStatement()) {

			ResultSet rs = ps.executeQuery(GET_PRODUCTS);
			while (rs.next()) {
				Product p = new Product().setId(rs.getInt("ID")).setName(rs.getString("NAME"))
						.setPrice(rs.getInt("PRICE")).setDescription(rs.getString("DESCRIPTION"))
						.setCategory(rs.getInt("CATEGORY"));

				products.add(p);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return products;
	}
	
	public String hashPassword(String s) {
				String password = s;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(StandardCharsets.UTF_8.encode(password+SALT));
			password = String.format("%032x", new BigInteger(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
	
}
