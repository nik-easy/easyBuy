package com.easyBuy.dal;

import java.util.List;
import java.util.Map;


public interface ProductsDAL {

	List<Map<String, Object>> getAllProducts(String productId);

	List<Map<String, Object>> getAllProductsByShopKeeperId(String shopkeeperId);
}