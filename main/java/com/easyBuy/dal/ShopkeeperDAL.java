package com.easyBuy.dal;

import java.util.List;
import java.util.Map;


public interface ShopkeeperDAL {

	List<Map<String, Object>> getAllShopkeepers(String shopkeeperId);

}