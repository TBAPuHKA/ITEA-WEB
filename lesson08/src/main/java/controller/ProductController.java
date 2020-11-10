package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.DBwork;

public class ProductController extends HttpServlet {

    private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(PRODUCT_FORM);
	    DBwork db = new DBwork();
	    List<Product> lp = db.getProducts();
	    
	    request.setAttribute("products", lp);
		rd.forward(request, response);
	}

	
}
