package org.prizy.pricer.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.prizy.pricer.dao.ProductDao;
import org.prizy.pricer.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("prouductDao")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public void saveProduct(Product product){
		sessionFactory.getCurrentSession().saveOrUpdate(product);
	}
	
	@Override
	public Product getProductById(int productId){
		Product product = (Product) sessionFactory.getCurrentSession()
			.createCriteria(Product.class, "product")
			.add(Restrictions.eq("productId", productId)).uniqueResult();
		return product;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductList() {
		List<Product> productList = sessionFactory
				.getCurrentSession()
				.createCriteria(Product.class, "product").list();
		return productList;

	}

}
