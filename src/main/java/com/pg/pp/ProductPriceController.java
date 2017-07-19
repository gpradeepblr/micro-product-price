package com.pg.pp;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductPriceController {
	
	protected Logger logger = Logger
			.getLogger(ProductPriceController.class.getName());

	@Autowired
	ProductPriceRepository productPriceRepository;
	
	@RequestMapping("/productprices")
	public ProductPrice[] all() {
		logger.info("product-prices-micros --> all() invoked");
		List<ProductPrice> allProductPrices = (List<ProductPrice>) productPriceRepository.findAll();
		logger.info("product-prices-micros --> all() retrieved : " + allProductPrices.size());
		return allProductPrices.toArray(new ProductPrice[allProductPrices.size()]);
	}
	
	@GetMapping(path="/addproductprice") // Map ONLY GET Requests
	public @ResponseBody String addNewProductPrice(@RequestParam Long productId
			, @RequestParam Double price) {
		
		logger.info("product-prices-micros --> addNewProductPrice() invoked");
		
		ProductPrice productPrice = new ProductPrice();
		productPrice.setProductId(productId);
		productPrice.setPrice(price);
		productPriceRepository.save(productPrice);
		return "added";
	}

	@RequestMapping("/productprice/{productid}")
	public ProductPrice byProductId(@PathVariable("productid") Long productId) {
		logger.info("product-prices-micros --> byProductId() invoked");
		ProductPrice productPrice = productPriceRepository.findByProductId(productId);
		logger.info("product-price-micros --> byProductId() retrieved : " + productPrice);
		return productPrice;
	}
}