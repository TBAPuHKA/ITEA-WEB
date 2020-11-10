package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DAOFactory;
import service.UserDAO;


public class RegUserController extends HttpServlet{
	
	private static final String REG_FORM = "WEB-INF/views/regFormView.jsp";
	private StringBuilder errorText = new StringBuilder();
	private Pattern pattern;
	private Matcher matcher;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(REG_FORM);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean isError = false;
		HttpSession session = req.getSession();
		User user = new User();
		
		user.setLogin(req.getParameter("login"));
		user.setPassword(req.getParameter("password"));
		user.setRePassword(req.getParameter("rePassword"));
		user.setName(req.getParameter("name"));
		user.setGender(req.getParameter("gender"));
		user.setAddress(req.getParameter("address"));
		user.setComment(req.getParameter("comment"));
		user.setAgree(req.getParameter("agree"));

		errorText.append("<ul>");
		
		if (user.getLogin() != null) {
			if (user.getLogin().isEmpty()) {
				errorText.append("<li>The 'login' is empty.</li>");
				isError = true;
			} else {
				pattern = Pattern.compile("^[\\w\\.-]+@[a-zA-Z0-9-]+\\.([a-z]{2}|com|org|net|edu|gov|info)$");
				matcher = pattern.matcher(user.getLogin());
				if (!matcher.matches()) {
					errorText.append("<li>The 'login' must be an email address.</li>");
					isError = true;
				}
			}
			if (user.getPassword().isEmpty()) {
				errorText.append("<li>The 'password' is empty.</li>");
				isError = true;
			}
			if (user.getRePassword().isEmpty()) {
				errorText.append("<li>The 'rePassword' is empty.</li>");
				isError = true;
			} else {
				if (!user.getPassword().isEmpty()) {
					if (!user.getPassword().equals(user.getRePassword())) {
						errorText.append("<li>The 'password' and 'rePassword' must be the same.</li>");
						isError = true;
					} else {
						pattern = Pattern.compile("^(?=.*[A-Z])[a-zA-Z0-9]{8,}$");
						matcher = pattern.matcher(user.getPassword());
						if (!matcher.matches()) {
							errorText.append(
									"<li>The 'password' must consist of numbers and letters<br>(at least one must be capital).<br>Length must be at least 8 characters long.</li>");
							isError = true;
						}
					}
				}
			}
			if (user.getName().isEmpty()) {
				errorText.append("<li>The 'name' is empty.</li>");
				isError = true;
			}
			if (user.getGender() == null) {
				errorText.append("<li>The 'gender' is empty.</li>");
				isError = true;
			}
			if (user.getAddress().isEmpty()) {
				errorText.append("<li>The 'address' is empty.</li>");
				isError = true;
			}
			if (user.getComment().isEmpty()) {
				errorText.append("<li>The 'comment' is empty.</li>");
				isError = true;
			}
			if (user.getAgree() == null) {
				errorText.append("<li>The 'agree' is empty.</li>");
				isError = true;
			}
		}
		
		errorText.append("</ul>");
		req.setAttribute("user", user);
		
		if (!isError) {
			DAOFactory daoFactory = DAOFactory.getInstance(Integer.valueOf(1));
			UserDAO userDAO = daoFactory.getUserDAO();
			userDAO.insertUser(user);
			session.setAttribute("logIn", user);
		} else {
			req.setAttribute("errorText", errorText.toString());
		}
		
		rd = req.getRequestDispatcher(REG_FORM);
		rd.forward(req, resp);
		errorText.delete(0, errorText.length());
	}

}
