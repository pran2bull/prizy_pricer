package org.prizy.pricer.controller;

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
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView viewProductList(@RequestParam(value="status",
			required=false) String status){
		ModelAndView modelView = new ModelAndView("product_grid");
		if(status!=null){
			modelView.addObject("save",status);
		}
		modelView.addObject("productList", productService.getProductList());
		return modelView;
	}
	
	@RequestMapping(value = "/new", method=RequestMethod.GET)
	public ModelAndView viewProduct(){
		ModelAndView modelView = new ModelAndView("product");
		return modelView;
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product){
		String status = "true";
		try{
			productService.saveProduct(product);
		}catch(ServiceException e){
			status = "false";
		}
		return "redirect:/product?status="+status;
	}

	@RequestMapping(value="/{productId}/edit", method=RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable("productId") int productId){
		ModelAndView modelView = new ModelAndView("product");
		modelView.addObject("product", productService.getProduct(productId));
		return modelView;
	}
	
	@RequestMapping(value="/{productId}/view", method=RequestMethod.GET)
	public ModelAndView viewProduct(@PathVariable("productId") int productId){
		ModelAndView modelView = new ModelAndView("product_report");
		modelView.addObject("productMap",productService.getProductMIS(productId));
		return modelView;
	}
}
