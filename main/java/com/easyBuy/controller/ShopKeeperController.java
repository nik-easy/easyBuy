package com.easyBuy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easyBuy.dal.ShopkeeperDAL;

@RestController
@RequestMapping(value = "/shopkeepers")
public class ShopKeeperController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final ShopkeeperDAL shopkeeperDAL;

	public ShopKeeperController( ShopkeeperDAL shopkeeperDAL) {
		this.shopkeeperDAL = shopkeeperDAL;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Map<String, Object>> getAllShopKeepers() {
		LOG.info("Getting all users.");
		return shopkeeperDAL.getAllShopkeepers(null);
	}

}