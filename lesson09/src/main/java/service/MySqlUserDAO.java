package service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class MySqlUserDAO implements UserDAO {

	private final String SELECT_USER = "SELECT NAME FROM `users` WHERE LOGIN = ? AND PASSWORD = ?;";
	private final String INSERT_USER = "INSERT INTO `users` (`login`, `password`, `name`, `gender`, `address`, `comment`) VALUES (?,?,?,?,?,?)";
	private final String SALT = "askl1$zQ%11w";

	MySqlDAOFactory ms;

	MySqlUserDAO(MySqlDAOFactory ms) {
		this.ms = ms;
	}

	@Override
	public User getLogin(String login, String password) {
		User auth = null;
		Connection conn = ms.getConnection();
		try (PreparedStatement ps = conn.prepareStatement(SELECT_USER);) {
			ps.setString(1, login);
			ps.setString(2, hashPassword(password + SALT));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				auth = new User();
				auth.setName(rs.getString("NAME"));
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
		return auth;
	}

	@Override
	public void insertUser(User reg) {
		PreparedStatement pst = null;
		Connection conn = ms.getConnection();

		try {
			pst = conn.prepareStatement(INSERT_USER);
			pst.setString(1, reg.getLogin());
			pst.setString(2, hashPassword(reg.getPassword() + SALT));
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

	@Override
	public String hashPassword(String s) {
		String password = s;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(StandardCharsets.UTF_8.encode(password + SALT));
			password = String.format("%032x", new BigInteger(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

}
