package com.easyBuy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easyBuy.dal.ProductsDAL;
import com.easyBuy.model.Products;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());


	private final ProductsDAL productsDAL;

	public ProductController( ProductsDAL userDAL) {
		this.productsDAL = userDAL;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHello() {
		return "Hello";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Map<String, Object>> getAllProducts() {
		LOG.info("Getting all products.");
		return productsDAL.getAllProducts(null);
	}

	@RequestMapping(value = "/{shopkeeperId}", method = RequestMethod.GET)
	public List<Map<String, Object>> getAllProductsOfShopKeeper(@PathVariable String shopkeeperId) {
		LOG.info("Getting all products.");
		return productsDAL.getAllProductsByShopKeeperId(shopkeeperId);
	}

}