package auth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.DBwork;
import auth.model.Auth;

public class AuthController extends HttpServlet {

	private String errorText = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/authFormView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();

		String login = req.getParameter("f1");
		String password = req.getParameter("f2");

		if (req.getParameter("logout") != null) {
			session.setAttribute("logIn", null);
			rd = req.getRequestDispatcher("WEB-INF/views/authFormView.jsp");
		} else {
			if (login.isEmpty() || password.isEmpty()) {
				errorText = "Login or Password isn't right!";
				req.setAttribute("errorText", errorText);
				rd = req.getRequestDispatcher("WEB-INF/views/authFormView.jsp");
			} else {
				DBwork dbWorker = new DBwork();
				Auth auth = dbWorker.getName(login, password);
				if (session.getAttribute("logIn") == null) {
					if (auth == null) {
						errorText = "Login or Password isn't right!";
						req.setAttribute("errorText", errorText);
						rd = req.getRequestDispatcher("WEB-INF/views/authFormView.jsp");
					} else {
						req.setAttribute("auth", auth);
						session.setAttribute("logIn", auth);
						rd = req.getRequestDispatcher("WEB-INF/views/authDoneView.jsp");
					}
				} else {
					req.setAttribute("auth", auth);
					session.setAttribute("logIn", auth);
					rd = req.getRequestDispatcher("WEB-INF/views/authDoneView.jsp");
				}
			}
		}

		rd.forward(req, resp);

	}
}
