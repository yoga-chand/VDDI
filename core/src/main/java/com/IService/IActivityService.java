package com.IService;


import java.util.List;

import com.model.MdnDetails;

public interface IActivityService {
	
	List<String> getActivityList(List<MdnDetails> mdnDetails, List<String> activities);

}
