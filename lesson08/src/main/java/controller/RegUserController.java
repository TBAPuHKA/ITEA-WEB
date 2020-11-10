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

import model.User;
import service.DBwork;


public class RegUserController extends HttpServlet{
	
	private static final String REG_FORM = "WEB-INF/views/regFormView.jsp";
	private static final String REG_DONE_FORM = "WEB-INF/views/regDoneView.jsp";
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
		User reg = new User();
		
		reg.setLogin(req.getParameter("login"));
		reg.setPassword(req.getParameter("password"));
		reg.setRePassword(req.getParameter("rePassword"));
		reg.setName(req.getParameter("name"));
		reg.setGender(req.getParameter("gender"));
		reg.setAddress(req.getParameter("address"));
		reg.setComment(req.getParameter("comment"));
		reg.setAgree(req.getParameter("agree"));

		errorText.append("<ul>");
		
		if (reg.getLogin() != null) {
			if (reg.getLogin().isEmpty()) {
				errorText.append("<li>The 'login' is empty.</li>");
				isError = true;
			} else {
				pattern = Pattern.compile("^[\\w\\.-]+@[a-zA-Z0-9-]+\\.([a-z]{2}|com|org|net|edu|gov|info)$");
				matcher = pattern.matcher(reg.getLogin());
				if (!matcher.matches()) {
					errorText.append("<li>The 'login' must be an email address.</li>");
					isError = true;
				}
			}
			if (reg.getPassword().isEmpty()) {
				errorText.append("<li>The 'password' is empty.</li>");
				isError = true;
			}
			if (reg.getRePassword().isEmpty()) {
				errorText.append("<li>The 'rePassword' is empty.</li>");
				isError = true;
			} else {
				if (!reg.getPassword().isEmpty()) {
					if (!reg.getPassword().equals(reg.getRePassword())) {
						errorText.append("<li>The 'password' and 'rePassword' must be the same.</li>");
						isError = true;
					} else {
						pattern = Pattern.compile("^(?=.*[A-Z])[a-zA-Z0-9]{8,}$");
						matcher = pattern.matcher(reg.getPassword());
						if (!matcher.matches()) {
							errorText.append(
									"<li>The 'password' must consist of numbers and letters<br>(at least one must be capital).<br>Length must be at least 8 characters long.</li>");
							isError = true;
						}
					}
				}
			}
			if (reg.getName().isEmpty()) {
				errorText.append("<li>The 'name' is empty.</li>");
				isError = true;
			}
			if (reg.getGender() == null) {
				errorText.append("<li>The 'gender' is empty.</li>");
				isError = true;
			}
			if (reg.getAddress().isEmpty()) {
				errorText.append("<li>The 'address' is empty.</li>");
				isError = true;
			}
			if (reg.getComment().isEmpty()) {
				errorText.append("<li>The 'comment' is empty.</li>");
				isError = true;
			}
			if (reg.getAgree() == null) {
				errorText.append("<li>The 'agree' is empty.</li>");
				isError = true;
			}
		}
		
		errorText.append("</ul>");
		req.setAttribute("reg", reg);
		
		if (!isError) {
			DBwork dbWork = new DBwork();
			dbWork.insertUser(reg);
			rd = req.getRequestDispatcher(REG_DONE_FORM);
		} else {
			req.setAttribute("errorText", errorText.toString());
			rd = req.getRequestDispatcher(REG_FORM);
		}
		
		rd.forward(req, resp);
		errorText.delete(0, errorText.length());
	}

}
