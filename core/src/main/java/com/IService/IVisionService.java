package com.IService;

import com.model.Usage;

public interface IVisionService {
	
	
	public Usage getMdnUsageDetail(String mdn);
	
	public boolean isDataExceeded(Usage usage);
	
	public boolean isTextExceeded(Usage usage);
	
	public boolean isMinuteExceeded(Usage usage);
		

	

}
