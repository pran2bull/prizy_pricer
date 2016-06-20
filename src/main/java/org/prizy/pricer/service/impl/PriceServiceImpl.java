package org.prizy.pricer.service.impl;

import java.util.List;

import org.prizy.pricer.dao.PriceDao;
import org.prizy.pricer.domain.Price;
import org.prizy.pricer.exception.ServiceException;
import org.prizy.pricer.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("priceService")
public class PriceServiceImpl implements PriceService {


	private static Logger log = LoggerFactory
			.getLogger(PriceServiceImpl.class);
	
	@Autowired
	private PriceDao priceDao;
	
	@Override
	@Transactional(rollbackFor=ServiceException.class)
	public void saveProductPrice(Price price) throws ServiceException{
		try{
		priceDao.saveProductPrice(price);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("Product price save failure");
		}
	}
	
	@Override
	@Transactional
	public int saveUserPrice(Price price){
		int status = 0;
		try{
			saveProductPrice(price);
			status = 1;
		}catch(ServiceException e){
			log.error("Product Price Error : {}",e.getMessage());
			status = 0;
		}
		return status;
	}
	
	@Override
	@Transactional
	public List<Price> getPrice(int productId){
		List<Price> priceList = priceDao.getPriceList(productId);
		return priceList;
	}
}
