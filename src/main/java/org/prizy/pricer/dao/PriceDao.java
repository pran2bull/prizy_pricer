package org.prizy.pricer.dao;

import java.util.List;

import org.prizy.pricer.domain.Price;

public interface PriceDao {

	void saveProductPrice(Price price);

	List<Price> getPriceList(int productId);

}
