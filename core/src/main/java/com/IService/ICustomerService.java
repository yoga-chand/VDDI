package com.IService;

import java.util.Map;

import com.model.Usage;

public interface ICustomerService {

	public Map<Boolean,String> intelligentAnswer(String mdn);
	
	public Usage getMdnUsage(String mdn);
	
	
	
}
