package org.prizy.pricer.controller;

import org.prizy.pricer.domain.Price;
import org.prizy.pricer.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

	@Autowired
	private PriceService priceService;
	
	@RequestMapping(value="/api/user/save", method=RequestMethod.POST)
	public @ResponseBody int saveProductPrice(@ModelAttribute("price") Price price){
		int status = priceService.saveUserPrice(price);
		return status;
	}
}
