package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.DBwork;
import model.User;

public class AuthUserController extends HttpServlet {
	private static final String LOGIN_FORM = "WEB-INF/views/authFormView.jsp";
	private String errorText = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(LOGIN_FORM);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();

		String login = req.getParameter("f1");
		String password = req.getParameter("f2");

		if (req.getParameter("logOut") != null) {
			session.setAttribute("logIn", null);
			rd = req.getRequestDispatcher(LOGIN_FORM);
		} else {
			if (login.isEmpty() || password.isEmpty()) {
				errorText = "Login or Password isn't right!";
				req.setAttribute("errorText", errorText);
				rd = req.getRequestDispatcher(LOGIN_FORM);
			} else {
				DBwork dbWorker = new DBwork();
				User user = dbWorker.getLogin(login, password);
				if (user == null) {
					errorText = "Login or Password isn't right!";
					req.setAttribute("errorText", errorText);
					rd = req.getRequestDispatcher(LOGIN_FORM);
				} else {
					req.setAttribute("user", user);
					session.setAttribute("logIn", user);
					rd = req.getRequestDispatcher(LOGIN_FORM);
				}

			}
		}

		rd.forward(req, resp);

	}
}
