package service;

import java.util.List;

import model.Product;

public interface ProductDAO {

	
	List<Product> getProducts();

	List<Product> getProductsByCategory(int categoryId);

	Product getProductById(int id);

}
