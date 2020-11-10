package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.DAOFactory;
import service.ProductDAO;

public class ProductController extends HttpServlet {

    private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(PRODUCT_FORM);
		DAOFactory daoFactory = DAOFactory.getInstance(Integer.valueOf(1));
		ProductDAO productDAO = daoFactory.getProductDAO();
		
		String category = request.getParameter("catId");
	    
		List<Product> lp = new ArrayList<Product>();
		if (category != null) {
			lp = productDAO.getProductsByCategory(Integer.valueOf(category));
		} else {
			lp = productDAO.getProducts();;
		}
	    
	    request.setAttribute("products", lp);
		rd.forward(request, response);
	}

	
}
