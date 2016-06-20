package org.prizy.pricer.service;

import java.util.List;

import org.prizy.pricer.domain.Price;
import org.prizy.pricer.exception.ServiceException;

public interface PriceService {

	void saveProductPrice(Price price) throws ServiceException;
	
	int saveUserPrice(Price price);

	List<Price> getPrice(int productId);

}
