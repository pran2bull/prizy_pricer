package org.prizy.pricer.service;

import java.util.List;
import java.util.Map;

import org.prizy.pricer.domain.Product;
import org.prizy.pricer.exception.ServiceException;

public interface ProductService {

	void saveProduct(Product product)  throws ServiceException;

	Product getProduct(int productId);

	List<Product> getProductList();

	Map<String, Object> getProductMIS(int productId);
}
