package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		List<Product> products = new ArrayList<Product>();
		
		if(productId!=null) {
			if (session.getAttribute("cart") != null) {
				products = (List<Product>) session.getAttribute("cart");
			}
			
			DAOFactory daoFactory = DAOFactory.getInstance(Integer.valueOf(1));
			ProductDAO productDAO = daoFactory.getProductDAO();

			products.add(productDAO.getProductById(Integer.valueOf(productId)));
		}
		
		if(productIdDel!=null) {
			products = (List<Product>) session.getAttribute("cart");
			products.removeIf(n -> Integer.valueOf(n.getId()).equals(Integer.valueOf(productIdDel)));
		}

		session.setAttribute("cart", products);
		resp.sendRedirect("./products");
	}
}