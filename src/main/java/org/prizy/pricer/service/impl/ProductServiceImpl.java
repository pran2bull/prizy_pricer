package org.prizy.pricer.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.prizy.pricer.dao.ProductDao;
import org.prizy.pricer.domain.Price;
import org.prizy.pricer.domain.Product;
import org.prizy.pricer.exception.ServiceException;
import org.prizy.pricer.service.PriceService;
import org.prizy.pricer.service.ProductService;
import org.prizy.pricer.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private Environment env;

	@Autowired
	private PriceService priceService;

	private static Logger log = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public void saveProduct(Product product) throws ServiceException {
		try {
			if (product.getProductId() == 0) {
				String uniqueNumber = System.currentTimeMillis()
						+ CommonUtil.generateRandomNumber();
				product.setBarCode(Long.parseLong(uniqueNumber));
			}
			productDao.saveProduct(product);
		} catch (Exception e) {
			log.error("Save Product Error : {}" + e.getMessage());
			throw new ServiceException("Save Product Error");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Product getProduct(int productId) {
		return productDao.getProductById(productId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Product> getProductList() {
		return productDao.getProductList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<String, Object> getProductMIS(int productId) {
		int upperLimit = Integer.parseInt(env.getProperty("ideal.upper.limit"));
		int lowerLimit = Integer.parseInt(env.getProperty("ideal.lower.limit"));
		int percentAdd = Integer.parseInt(env.getProperty("ideal.add.percent"));
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put("product", getProduct(productId));
		List<Price> priceList = priceService.getPrice(productId);
		if (priceList != null && priceList.size() > 0) {
			int priceCount = priceList.size();
			productMap.put("priceCount", priceCount);
			productMap.put("highestPrice", priceList.get(priceList.size() - 1)
					.getPrice());
			productMap.put("lowestPrice", priceList.get(0).getPrice());
			long averagePrice = 0;
			long idealPrice = 0;
			int limitSum = upperLimit + lowerLimit;
			for (int i = 0; i < priceCount; i++) {
				averagePrice = averagePrice + priceList.get(i).getPrice();
				if (priceCount > limitSum) {
					if ((i > (upperLimit - 1))
							&& (priceCount - i > (lowerLimit))) {
						idealPrice = idealPrice + priceList.get(i).getPrice();
					}
				}
			}
			try {
				productMap.put("averagePrice", averagePrice / priceCount);
			} catch (Exception e) {
				log.error("Exception for idealPrice : {}" + e.getMessage());
			}
			if (priceCount > limitSum) {
				try {
					long avgIdealPrice = idealPrice / (priceCount - limitSum);
					double percentAvgPrice = (double) avgIdealPrice * ((double) percentAdd / 100);
					productMap.put("idealPrice", avgIdealPrice
							+ percentAvgPrice);
				} catch (Exception e) {
					log.error("Exception for idealPrice : {}" + e.getMessage());
				}
			}
			productMap.put("limitSum", limitSum);
		}
		return productMap;
	}
}
