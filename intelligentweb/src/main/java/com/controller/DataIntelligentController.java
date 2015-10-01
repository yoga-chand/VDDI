package com.controller;
import com.model.ActivityObj;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Order;
import com.IService.ICustomerService;
import com.IService.IVisionService;
import com.model.MdnDetails;
import com.model.Usage;
import com.serviceImpl.ActivityServiceImpl;
import com.serviceImpl.CustomerServiceImpl;
import com.serviceImpl.RecentOrders;
import com.serviceImpl.VisionServiceImpl;

public class DataIntelligentController extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
					{
		// Set response content type
		response.setContentType("text/html");

		String responseString = null;
		boolean isAvailable = false;
		String mdn = request.getParameter("mdn");

		ICustomerService customerService = new CustomerServiceImpl();
		Map<Boolean, String> responseMap = null;
		responseMap= customerService.intelligentAnswer(mdn);
		System.out.println("outage response "+responseMap.toString());

		if(responseMap.containsKey(true)){
			responseString = responseMap.get(true);
		}
		else{
			IVisionService visionService = new VisionServiceImpl();

			Usage usage = visionService.getMdnUsageDetail(mdn);

			if(usage!=null){
				if(visionService.isDataExceeded(usage)){
					isAvailable = true;
					responseString = "You have exceeded the data usage. New data plans are introduced which will make you happy. Press 7 for the details.";

				}
				else if(visionService.isTextExceeded(usage)){
					isAvailable = true;
					responseString = "You have exceeded the text usage. New message plans are introduced which will make you happy. Press 8 for the details.";

				}
				else if(visionService.isMinuteExceeded(usage)){
					isAvailable = true;
					responseString = "You have exceeded the minutes usage. New voice plans are introduced which will make you happy. Press 6 for the details.";

				}
			}
			if(!isAvailable){

				List<MdnDetails> mdnDetailsList = ActivityServiceImpl.getMdnDetailFromDB(mdn);
				List<String> asList = new ArrayList<String>();
				asList = ActivityServiceImpl.getActivityList(mdnDetailsList, asList);
				List<ActivityObj> actList = new ArrayList<ActivityObj>();
				if(mdnDetailsList.size()>0){
					responseMap = ActivityServiceImpl.isChronic(mdnDetailsList);
					if(responseMap.containsKey(true)){
						responseString = responseMap.get(true);
					}
					else{
						Set<String> mySet = new HashSet<String>(asList);
						for(String s: mySet){
							System.out.println(s + " " +Collections.frequency(asList,s));
							actList.add(new ActivityObj(s,Collections.frequency(asList,s)));
						}
						ActivityObj activityObj=ActivityServiceImpl.getMaxOccuredActivity(actList);
						if(activityObj!=null && activityObj.getValue()>1){
							if(activityObj.getActivity().equalsIgnoreCase("billenquiry")){
								responseString = "Your bill amount is 100$ and is due on 7th Oct 2015";
							}
							else if(activityObj.getActivity().equalsIgnoreCase("paymentinfo")){
								responseString = "Your last payment amount is 130$ paid on 4th Oct 2015";
							}

						}

					}
				}
				else{
					try {
						Order order = new RecentOrders().getOrderInfo(mdn);
						if(order!=null){
							responseString = "Your order "+order.getOrderid()+ " "+ order.getOrdername()+" is on the way and will be delivered on "+order.getExpecteddelivery();
						}
						else{
							responseString = "There are no suggestions for this number.";
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}



		}

		//}
		response.getWriter().println(responseString);
	}



}





