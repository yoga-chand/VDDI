package com.model;

public class Order {

	private String orderid;
	private String ordername;
	private String expecteddelivery;
	private String mdn;
	private IdObject _id;
	
	public IdObject get_id() {
		return _id;
	}
	public void set_id(IdObject _id) {
		this._id = _id;
	}	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public String getExpecteddelivery() {
		return expecteddelivery;
	}
	public void setExpecteddelivery(String expecteddelivery) {
		this.expecteddelivery = expecteddelivery;
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	
	
}
