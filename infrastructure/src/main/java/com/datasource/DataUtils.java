package com.datasource;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.*;

public class DataUtils {

	private static DataUtils dataUtils;
	
	private DataUtils(){
		
	}
	
	public static DataUtils getInstance(){
		
		if(dataUtils == null){
			synchronized (DataUtils.class) {
				dataUtils = new DataUtils();
			}
		}
		return dataUtils;
	}
	
	public static MongoClient getConnection(String database){
		MongoClient mongo = null;
		try {
			
		   MongoClientURI uri  = new MongoClientURI("mongodb://yoga:test123@ds051863.mongolab.com:51863/CloudFoundry_omfu0lp3_t4cigvf3"); 
        	   mongo = new MongoClient(uri);
        	   DB db = client.getDB(uri.getDatabase());
		   
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mongo;
	}
		
		
	
}

