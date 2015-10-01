package com.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import com.IRepository.ILocationOutage;
import com.model.LocationOutage;

public class LocationOutageImpl implements ILocationOutage{

	public List<LocationOutage> getLocationOutages() {
		List<LocationOutage> locationOutages = new ArrayList<LocationOutage>();
		LocationOutage locationOutage = new LocationOutage();
		locationOutage.setOutageCity("Newyork");
		locationOutage.setOutageStreet("Louis street");
		locationOutages.add(locationOutage);
		
		LocationOutage locationOutage2 = new LocationOutage();
		locationOutage2.setOutageCity("Hudson County");
		locationOutage2.setOutageStreet("New Jersey");
		locationOutages.add(locationOutage2);
		return locationOutages;
	}

}
