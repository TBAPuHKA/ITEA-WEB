package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import service.DAOFactory;
import service.ProductDAO;

public class CartController extends HttpServlet {

	private static final String CART_FORM = "WEB-INF/views/cartView.jsp";

	public CartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(CART_FORM);
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("prodId");
		String productIdDel = req.getParameter("prodIdDel");
		HttpSession session = req.getSession();

		Map<Product, Integer> products = new HashMap<>();
		Product product = new Product();

		if (session.getAttribute("cart") != null) {
			products = (Map<Product, Integer>) session.getAttribute("cart");
		}
		if (productId != null) {
			int amount = 1;
			DAOFactory daoFactory = DAOFactory.getInstance(Integer.valueOf(1));
			ProductDAO productDAO = daoFactory.getProductDAO();

			product = productDAO.getProductById(Integer.valueOf(productId));
			
			if (products.get(product) != null) {
				amount = products.get(product) + 1;
			}
			products.put(product, amount);
		}
		
		if (productIdDel != null) {
			DAOFactory daoFactory = DAOFactory.getInstance(Integer.valueOf(1));
			ProductDAO productDAO = daoFactory.getProductDAO();
			
			product = productDAO.getProductById(Integer.valueOf(productIdDel));
			products.remove(product);
		}

		session.setAttribute("cart", products);
		resp.sendRedirect("./cart");
	}
}