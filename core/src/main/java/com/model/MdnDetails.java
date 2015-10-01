package com.model;

import java.io.Serializable;

import com.mongodb.BasicDBObject;

public class MdnDetails extends BasicDBObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2857642152947381901L;

	private String calldate;
	
	private String calltime;
	
	private String activity;
	
	private String street;
	

	private String city;

	public MdnDetails(){
		
	}
	
	public String getCalldate() {
		return super.getString("calldate");
		//return calldate;
	}

	public void setCalldate(String calldate) {
		super.put("calldate", calldate);
		//this.calldate = calldate;
	}

	public String getCalltime() {
		return super.getString("calltime");
		//return calltime;
	}

	public void setCalltime(String calltime) {
		super.put("calltime", calltime);
		//this.calltime = calltime;
	}

	public String getActivity() {
		return super.getString("activity");
		//return activity;
	}

	public void setActivity(String activity) {
		super.put("activity", activity);
		//this.activity = activity;
	}

	public String getStreet() {
		return super.getString("street");
		//return street;
	}

	public void setStreet(String street) {
		super.put("street", street);
		//this.street = street;
	}

	public String getCity() {
		return super.getString("city");
		//return city;
	}

	public void setCity(String city) {
		super.put("city", city);
		//this.city = city;
	}
	
	

}
