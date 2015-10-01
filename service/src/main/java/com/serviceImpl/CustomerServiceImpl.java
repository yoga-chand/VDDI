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
		
		List<LocationOutage> locationOutages = getLocationOutageInfo();
		CustomerDetails customerDetails = getCustomerDetails(mdn);
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

		String database = "customerdetails";
		Mongo mongo = DataUtils.getConnection(database);
		CustomerDetails customerDetails = null;
		try{
			DB db = mongo.getDB(database);
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
			e.printStackTrace();
		}
		catch(Exception e){
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
