package com.easyBuy.dal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Repository
public class ProductsDALImpl implements ProductsDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Map<String, Object>> getAllProducts(String productId) {

		List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
		Iterator<DBObject> dbobjIterator = null;
		if(StringUtils.isEmpty(productId))
			dbobjIterator = mongoTemplate.getCollection("products").find().iterator();
		else {
			BasicDBObject query = new BasicDBObject("_id", new BasicDBObject("$eq", new ObjectId(productId)));
			dbobjIterator = mongoTemplate.getCollection("products").find(query).iterator();
		}
		
		prepareProduct(products, dbobjIterator);
		return products;
	}

	private void prepareProduct(List<Map<String, Object>> products, Iterator<DBObject> dbobjIterator) {
		while (dbobjIterator.hasNext()) {
			Map<String, Object> product = dbobjIterator.next().toMap();
			product.put("_id", product.get("_id").toString());
			products.add(product);
		}
	}

	@Override
	public List<Map<String, Object>> getAllProductsByShopKeeperId(String shopkeeperId) {

		BasicDBObject query = new BasicDBObject("shopkeeperId", new BasicDBObject("$eq", shopkeeperId));

		List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
		Iterator<DBObject> dbobjIterator = mongoTemplate.getCollection("productShopkeeperDetails").find(query).iterator();
		while (dbobjIterator.hasNext()) {
			Map<String, Object> productShopkeeperDetail = dbobjIterator.next().toMap();
			products.addAll(getAllProducts(productShopkeeperDetail.get("productId").toString()));
		}
		
		return products;
	}

}
