package com.datasource;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

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
			mongo = new MongoClient("localhost", 27017);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mongo;
	}
		
		
	
}

