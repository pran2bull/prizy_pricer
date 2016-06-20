package org.prizy.pricer.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.prizy.pricer.dao.PriceDao;
import org.prizy.pricer.domain.Price;
import org.prizy.pricer.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("priceDao")
public class PriceDaoImpl implements PriceDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveProductPrice(Price price){
		sessionFactory.getCurrentSession().saveOrUpdate(price);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Price> getPriceList(int productId) {
		List<Price> priceList = sessionFactory
				.getCurrentSession()
				.createCriteria(Price.class, "price")
				.add(Restrictions.eq("productId", productId))
				.addOrder(Order.asc("price"))
				.list();
		return priceList;

	}
}
