package com.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;


public class CustomerDetails{
	
	private String mdn;
	
	private IdObject _id;
	
	
	
	public IdObject get_id() {
		return _id;
	}

	public void set_id(IdObject _id) {
		this._id = _id;
	}

	/*@JsonIgnore
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
*/
	private List<MdnDetails> mdndetails;

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public List<MdnDetails> getMdndetails() {
		return mdndetails;
	}

	public void setMdndetails(List<MdnDetails> mdndetails) {
		this.mdndetails = mdndetails;
	}

	
	
	

}
