package com.model;

import com.model.IdObject;


public class Usage {
	
	private String mdn;
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	private float dataused;
	
	private float dataavailable;
	
	private int minutesused;
	
	private int minutesavailable;
	
	private int textused;
	
	private int textavailable;
	
	private IdObject _id;
	
	public IdObject get_id() {
		return _id;
	}
	public void set_id(IdObject _id) {
		this._id = _id;
	}
	public float getDataused() {
		return dataused;
	}
	public void setDataused(float dataused) {
		this.dataused = dataused;
	}
	public float getDataavailable() {
		return dataavailable;
	}
	public void setDataavailable(float dataavailable) {
		this.dataavailable = dataavailable;
	}
	public int getMinutesused() {
		return minutesused;
	}
	public void setMinutesused(int minutesused) {
		this.minutesused = minutesused;
	}
	public int getMinutesavailable() {
		return minutesavailable;
	}
	public void setMinutesavailable(int minutesavailable) {
		this.minutesavailable = minutesavailable;
	}
	public int getTextused() {
		return textused;
	}
	public void setTextused(int textused) {
		this.textused = textused;
	}
	public int getTextavailable() {
		return textavailable;
	}
	public void setTextavailable(int textavailable) {
		this.textavailable = textavailable;
	}
	
		
}
