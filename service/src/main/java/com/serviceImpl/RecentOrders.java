package com.serviceImpl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.datasource.DataUtils;
import com.model.Order;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class RecentOrders {

	public Order getOrderInfo(String mdn) throws JsonParseException,
	JsonMappingException, IOException, ParseException {

		String database = "orders";
		Mongo mongo = null;
		Order order = null;
		try{
			MongoClientURI uri  = new MongoClientURI("mongodb://CloudFoundry_omfu0lp3_t4cigvf3_vc5m5ajq:D0pMgRG0Vq4g-thG5E2ERlTzmP_NvlwH@ds051863.mongolab.com:51863/CloudFoundry_omfu0lp3_t4cigvf3"); 
        	mongo = new MongoClient(uri);
        	DB db = mongo.getDB(uri.getDatabase());
        	boolean auth = db.authenticate("yoga", "test123".toCharArray());
        	System.out.println("db authenticated "+auth);
        	DBCollection col = db.getCollection(database);
			DBObject query = BasicDBObjectBuilder.start().add("mdn", mdn).get();
			DBCursor cursor = col.find(query);
			ObjectMapper mapper = new ObjectMapper();
			while(cursor.hasNext()){
				order = mapper.readValue(cursor.next().toString(), Order.class);
			}
			
			if (order != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
				String exp = order.getExpecteddelivery();
				Date expected = sdf.parse(exp);
				Date max = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(max);
				c.add(Calendar.DATE, -7);
				Date min = c.getTime();

				if (expected.compareTo(min) >= 0 || expected.compareTo(max) <= 0) {
					System.out.println("HEER");
					return order;
				} else {
					System.out.println("THERE");
					return order;
				}
			} else {
				return order;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			mongo.close();
		}
		return order;
	}
	
	

}
