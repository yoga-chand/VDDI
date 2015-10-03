package com.serviceImpl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.ActivityObj;

import org.codehaus.jackson.map.ObjectMapper;

import com.model.CustomerDetails;
import com.model.MdnDetails;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ActivityServiceImpl {


	public static List<String> getActivityList(List <MdnDetails> mdnDetails,List<String>asList){
		for(MdnDetails mdnDetail: mdnDetails){
			asList.add(mdnDetail.getActivity());
		}

		return asList;
	}
	public static List<String> getCallDatesList(List <MdnDetails> mdnDetails,List<String>asList){
		for(MdnDetails mdnDetail: mdnDetails){
			asList.add(mdnDetail.getCalldate());
		}

		return asList;
	}

	public static ActivityObj getMaxOccuredActivity(List <ActivityObj> actList){
		Collections.sort(actList, new Comparator<ActivityObj>() {
			public int compare(ActivityObj o1, ActivityObj o2) {
				return Double.compare(o1.getValue(), o2.getValue());
			}
		});		
		/*System.out.println(actList);
		for(ActivityObj a:actList){
			System.out.println(a.getActivity()+":"+a.getValue());
		}
		 */
		return actList.get(actList.size()-1);
	}


	public static List<MdnDetails> getMdnDetailFromDB(String mdn){

		MongoClient mongo = null;
		CustomerDetails customerDetails=null ;//= new XmlToJson().convertXmlToJSON("<customerdetails><mdn>1234567890</mdn><mdndetails><mdndetail><calldate>23-05-2015</calldate><calltime>21:15</calltime><activity>billenquiry</activity><street>portfield street</street><city>NJ</city></mdndetail><mdndetail><calldate>23-05-2015</calldate><calltime>21:15</calltime><activity>billenquiry</activity><street>portfield street</street><city>NJ</city></mdndetail></mdndetails></customerdetails>");
		List<MdnDetails> mdnDetailsList = new ArrayList<MdnDetails>();
		try {
			MongoClientURI uri  = new MongoClientURI("mongodb://CloudFoundry_omfu0lp3_t4cigvf3_vc5m5ajq:D0pMgRG0Vq4g-thG5E2ERlTzmP_NvlwH@ds051863.mongolab.com:51863/CloudFoundry_omfu0lp3_t4cigvf3"); 
        	mongo = new MongoClient(uri);
        	DB db = mongo.getDB(uri.getDatabase());
        	boolean auth = db.authenticate("yoga", "test123".toCharArray());
        	System.out.println("db authenticated "+auth);
			DBCollection col = db.getCollection("customerdetails");
			DBObject query = BasicDBObjectBuilder.start().add("mdn", mdn).get();
			//read example
			DBCursor cursor = col.find(query);
			ObjectMapper mapper = new ObjectMapper();		    
			while(cursor.hasNext()){
				try {

					customerDetails = mapper.readValue(cursor.next().toString(), CustomerDetails.class);
					System.out.println("CUSTOEMR INFO" + customerDetails.getMdn());
					mdnDetailsList = customerDetails.getMdndetails();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in getMdnDetailFromDB "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Exception in getMdnDetailFromDB "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			mongo.close();
		}
		return mdnDetailsList;
	}

	public static Map<Boolean, String> isChronic(List<MdnDetails> mdnDetails){
		List <String>asList = new ArrayList<String>();
		asList= getCallDatesList(mdnDetails,asList);
		Map<Boolean,String> responseMap= new HashMap<Boolean, String>();
		boolean isChronic=false;
		int callcount=0;
		for(String date:asList){
			Calendar c = Calendar.getInstance();

			// set the calendar to start of today
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);

			// and get that as a Date
			Date today = c.getTime();

			// or as a timestamp in milliseconds
			long todayInMillis = c.getTimeInMillis();

			// user-specified date which you are testing
			// let's say the components come from a form or something

			String dateArray[]=date.split("-");
			int year = Integer.parseInt(dateArray[2]);
			int month = Integer.parseInt(dateArray[1]);
			int dayOfMonth = Integer.parseInt(dateArray[0]);

			// reuse the calendar to set user specified date
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month-1);
			c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

			// and get that as a Date
			Date dateSpecified = c.getTime();

			// test your condition
			System.out.println("dateSpecified="+dateSpecified);
			System.out.println("today="+today);
			if (dateSpecified.equals(today)) {
				callcount++;
			}
			System.out.println("Number of calls for today="+callcount);
		}


		if(callcount>2){
			responseMap.put(true, "You will be transferred to an agent right away. Please hold.");
			System.out.println("More than 2 cal counts for today");
		}else{
			responseMap.put(false, "");
			System.out.println("Call count not exceeded for today");
		}
		return responseMap;
	}


}
