package org.prizy.pricer.dao;

import java.util.List;

import org.prizy.pricer.domain.Product;

public interface ProductDao {

	void saveProduct(Product product);
	
	Product getProductById(int productId);

	List<Product> getProductList();
}
