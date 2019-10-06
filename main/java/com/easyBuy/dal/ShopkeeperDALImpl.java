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
public class ShopkeeperDALImpl implements ShopkeeperDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Map<String, Object>> getAllShopkeepers(String shopkeeperId) {

		List<Map<String, Object>> shopkeepers = new ArrayList<Map<String, Object>>();
		Iterator<DBObject> dbobjIterator = null;
		if(StringUtils.isEmpty(shopkeeperId))
			dbobjIterator = mongoTemplate.getCollection("shopkeepers").find().iterator();
		else {
			BasicDBObject query = new BasicDBObject("_id", new BasicDBObject("$eq", new ObjectId(shopkeeperId)));
			dbobjIterator = mongoTemplate.getCollection("shopkeepers").find(query).iterator();
		}
		
		prepareProduct(shopkeepers, dbobjIterator);
		return shopkeepers;
	}

	private void prepareProduct(List<Map<String, Object>> products, Iterator<DBObject> dbobjIterator) {
		while (dbobjIterator.hasNext()) {
			Map<String, Object> product = dbobjIterator.next().toMap();
			product.put("_id", product.get("_id").toString());
			products.add(product);
		}
	}

}
