package com.model;

public class ActivityObj {

	private String activity;
	private Integer value;
	
	
	public ActivityObj(String activity,Integer value){
		
		this.activity=activity;
		this.value=value;
	}
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
