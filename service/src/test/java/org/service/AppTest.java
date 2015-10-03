package org.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.IService.ICustomerService;
import com.serviceImpl.CustomerServiceImpl;
import com.serviceImpl.RecentOrders;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @throws ParseException 
     */
    public void testApp() throws JsonParseException, JsonMappingException, IOException, ParseException
    {
    /*	System.out.println("CHECK FOR SERVICE PACKAGE!!!");
    	testJsonData();
    	//testJsonOrder();
        assertTrue( true );
        */
    }
    
    public void testJsonData() {
    //	ICustomerService customerService = new CustomerServiceImpl();
    //	Map<Boolean,String> mymap= customerService.intelligentAnswer("1234567890");
    //	System.out.println("Size of "+mymap.size());
    //	assertTrue(mymap.size()>0);
    }
    
    public void testJsonOrder() throws JsonParseException, JsonMappingException, IOException, ParseException {
    //	RecentOrders customerService = new RecentOrders();
    //	customerService.getOrderInfo("1234567890");
    	
    }
}
