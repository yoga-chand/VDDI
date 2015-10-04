package com.datasource;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
//import com.mongodb.MongoCredential;

import com.mongodb.*;

public class DataUtils {

	
	public static Map<String, Object> dbMap = new HashMap<String,Object>(); 
	
	public static boolean auth = false;
	
	
	
	public static synchronized Map<String, Object> getConnection(){
		
        MongoClient mongo = null;
        System.out.println("dbmap size "+dbMap.size());
        if(dbMap.size()==0){
        	
        		try { 
        			MongoClientURI uri  = new MongoClientURI("mongodb://CloudFoundry_omfu0lp3_t4cigvf3_vc5m5ajq:D0pMgRG0Vq4g-thG5E2ERlTzmP_NvlwH@ds051863.mongolab.com:51863/CloudFoundry_omfu0lp3_t4cigvf3");
			//	MongoCredential credential = MongoCredential.createCredential("yoga","CloudFoundry_omfu0lp3_t4cigvf3","test123".toCharArray());        			
        			//MongoClientURI uri  = new MongoClientURI("mongodb://yoga:test123@ds051863.mongolab.com:51863/?authSource=db1");
        			mongo = new MongoClient(uri);
        			DB db = mongo.getDB(uri.getDatabase());
        		    System.out.println("auth status "+DataUtils.auth);
        		    if(!DataUtils.auth){
        		    	DataUtils.auth = true;
        		      	//DataUtils.auth = db.authenticate("yoga", "test123".toCharArray()); 
        		      	System.out.println("db authenticated "+DataUtils.auth);
        		    }
        		    dbMap.put("mongo", mongo);
        		    dbMap.put("db", db);
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}		
		
        }
		return dbMap;
      
	}
	
}

