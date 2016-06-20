package org.prizy.pricer.controller;

import org.prizy.pricer.domain.Price;
import org.prizy.pricer.domain.Product;
import org.prizy.pricer.exception.ServiceException;
import org.prizy.pricer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrizyController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView viewPrizyPricer(){
		ModelAndView modelAndView = new ModelAndView("pricer");
		modelAndView.addObject("productList", productService.getProductList());
		return modelAndView;
	}
	
	
}
