package com.model;

import java.util.List;

public class Customer {

	String mdn;
	List<CustomerInfo> customerinfos;
	String _id;
	
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public List<CustomerInfo> getCustomerinfos() {
		return customerinfos;
	}
	public void setCustomerinfos(List<CustomerInfo> customerinfos) {
		this.customerinfos = customerinfos;
	}
	
	
}
