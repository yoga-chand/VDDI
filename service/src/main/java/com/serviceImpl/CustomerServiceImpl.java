package com.serviceImpl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.IRepository.ILocationOutage;
import com.IService.ICustomerService;
import com.RepositoryImpl.LocationOutageImpl;
import com.datasource.DataUtils;
import com.model.Customer;
import com.model.CustomerDetails;
import com.model.CustomerInfo;
import com.model.LocationOutage;
import com.model.MdnDetails;
import com.model.Usage;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class CustomerServiceImpl implements ICustomerService{

	ILocationOutage outage = new LocationOutageImpl(); 
	public Customer convertToCustomer(String json) {
		Customer customer = new Customer();
		JSONObject jsonObject = new JSONObject();
		List<CustomerInfo> customerInfos =new ArrayList<CustomerInfo>();
		CustomerInfo info = new CustomerInfo();
		info.setActivity("activity");
		info.setCallDate("DATE");
		info.setCallTime("TIME");
		info.setCity("CHENNAI");
		info.setStreet("Kolathur");
		customerInfos.add(info);
		jsonObject.put("mdn", "123456789");
		jsonObject.put("_id", "29");
		jsonObject.put("customerinfos", customerInfos);
		System.out.println(jsonObject.toString());

		try {
			ObjectMapper mapper = new ObjectMapper();
			customer = mapper.readValue(jsonObject.toString(), Customer.class);
			System.out.println("CUSTOEMR INFO" + customer.getMdn());
			List<CustomerInfo> customerInfos_response = customer.getCustomerinfos();
			System.out.println("CUSTOEMR INFO" + customerInfos_response.get(0).getStreet());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return customer;
	}

	public Map<Boolean, String> intelligentAnswer(String mdn) {
		Boolean isOutage=false;
		String response ="";
		Map<Boolean, String> responseMap = new HashMap<Boolean, String>();
		System.out.println("intelligent answer method");
		List<LocationOutage> locationOutages = getLocationOutageInfo();
		CustomerDetails customerDetails = getCustomerDetails(mdn);
	/*	if(mdn.equalsIgnoreCase("9176275565")){
			response = "There seem to be an outage in Louis Street, Newyork Our team is working on it. This will be sorted in 2 hours.";
			responseMap.put(true, response);
		}
		else{
			responseMap.put(false, response);
		}*/
			
		
	//	customerDetails.setMdndetails(new ArrayList<MdnDetails>().set(0, new MdnDetails().set));
		if(customerDetails!=null){
			if(customerDetails.getMdndetails().size()>0){
				MdnDetails mdnDetails = customerDetails.getMdndetails().get(0);
				for (LocationOutage locationOutage : locationOutages) {
					if ((locationOutage.getOutageStreet().equalsIgnoreCase(mdnDetails.getStreet())) && (locationOutage.getOutageCity().equalsIgnoreCase(mdnDetails.getCity())))
					{
						isOutage=true;
						response = "There seem to be an outage in " + mdnDetails.getStreet()+ ","+mdnDetails.getCity() +" Our team is working on it. This will be sorted in 2 hours.";
						responseMap.put(isOutage, response);
						break;
					}
					else{
						responseMap.put(false, "");
					}
				}		
			}
		}
		else{
			responseMap.put(false, "");
		}
		return responseMap;
	}

	public List<LocationOutage> getLocationOutageInfo() {
		// TODO Auto-generated method stub
		List<LocationOutage> locationOutages = outage.getLocationOutages();
		return locationOutages;
	}

public CustomerDetails getCustomerDetails(String mdn){

		String database = "CloudFoundry_omfu0lp3_t4cigvf3.customerdetails";
		CustomerDetails customerDetails = null;
		MongoClient mongo = null;
		try{
			System.out.println("getcustdetails");
			//Map<String,Object> dbMap = DataUtils.getConnection();
			//DB db = (DB)dbMap.get("db");
			//mongo = (MongoClient)dbMap.get("mongo");
			//MongoClientURI uri = new MongoClientURI("mongodb://yoga:test123@host1/?authSource=db1");
			MongoClientURI uri  = new MongoClientURI("mongodb://yoga:test123@ds051863.mongolab.com:51863/?authSource=CloudFoundry_omfu0lp3_t4cigvf3&authMechanism=SCRAM-SHA-1");
			System.out.println("after uri creation");
			//MongoClientURI uri  = new MongoClientURI("mongodb://CloudFoundry_omfu0lp3_t4cigvf3_vc5m5ajq:D0pMgRG0Vq4g-thG5E2ERlTzmP_NvlwH@ds051863.mongolab.com:51863/CloudFoundry_omfu0lp3_t4cigvf3"); 
        		mongo = new MongoClient(uri);
        		System.out.println("after mongo creation");
        		DB db = mongo.getDB(uri.getDatabase());
        		System.out.println("after db creation");
        		/*System.out.println("auth status in custservice impl "+DataUtils.auth);
        		if(!DataUtils.auth){
	        		db.authenticate("yoga", "test123".toCharArray()); 
        			System.out.println("db authenticated "+DataUtils.auth);
        		}*/
			DBCollection col = db.getCollection(database);
			DBObject query = BasicDBObjectBuilder.start().add("mdn", mdn).get();
			DBCursor cursor = col.find(query);
			ObjectMapper mapper = new ObjectMapper();	
			
			while(cursor.hasNext()){

				customerDetails = mapper.readValue(cursor.next().toString(), CustomerDetails.class);
				System.out.println("CUSTOEMR INFO" + customerDetails.getMdn());

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in intelligent answer unknown host "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Exception in intelligent answer "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			mongo.close();
		}
		return customerDetails;
	}


	public String intelligentAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Usage getMdnUsage(String mdn) {
		// TODO Auto-generated method stub
		return null;
	}


}
