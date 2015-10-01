package com.serviceImpl;
import java.io.IOException;
import java.net.UnknownHostException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.IService.IVisionService;
import com.datasource.DataUtils;
import com.model.Usage;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VisionServiceImpl implements IVisionService {


	public  Usage getMdnUsageDetail(String mdn){
		Usage usage=null;
		MongoClient mongo = null;
		String database = "customerdetails";
		try {
			mongo = DataUtils.getConnection(database);
			DB db = mongo.getDB(database);
			DBCollection col = db.getCollection("usage");
			DBObject query = BasicDBObjectBuilder.start().add("mdn", mdn).get();
			DBCursor cursor = col.find(query);
			ObjectMapper objectMapper = new ObjectMapper();
			if(cursor.hasNext()){
				//System.out.println(cursor.next());
				DBObject obj = cursor.next(); 
				usage = objectMapper.readValue(obj.toString(), Usage.class);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			mongo.close();
		}

		return usage;

	}
	
	public boolean isDataExceeded(Usage usage){
		if(usage.getDataused() > usage.getDataavailable()){
			return true;
		}
		return false;
	}
	
	public boolean isTextExceeded(Usage usage){
		if(usage.getTextused() > usage.getTextavailable()){
			return true;
		}
		return false;
	}
	
	public boolean isMinuteExceeded(Usage usage){
		if(usage.getMinutesused() > usage.getMinutesavailable()){
			return true;
		}
		return false;
	}
	
	
	/*  private static void createDBObject(Usage usage) {
		   MongoClient mongo = null;
		   try{ 
				mongo = new MongoClient("localhost", 27017);
				DB db = mongo.getDB("usage");

		   BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		    docBuilder.append("mdn", usage.getMdn());
		    docBuilder.append("data", usage.getData());
		    docBuilder.append("minutes", usage.getMinutes());
		    docBuilder.append("text", usage.getText());

		    DBCollection col = db.getCollection("usagedetail");

		    WriteResult result = col.insert(docBuilder.get());

		   }catch (UnknownHostException e) {
				e.printStackTrace();
			}
			finally{
				mongo.close();
			}


		}

	 */
}
