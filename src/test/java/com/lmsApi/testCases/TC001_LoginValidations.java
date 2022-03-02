package com.lmsApi.testCases; 
import org.testng.annotations.Test;

import com.lmsApi.dataProvider.DataProvider_LMS;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_LoginValidations  {
	 RequestSpecification httpRequest;
	 Response response;
	
	 @Test(priority=1)
	public void defaultAuthorization() {
		//specify baseURI
	    RestAssured.baseURI = "https://lms-admin-rest-service.herokuapp.com/programs";
	    
	    //request object
    	httpRequest = RestAssured.given();
    	
    	//response object
        response = httpRequest.get();
        
        //status code validation
        int statuscode = response.getStatusCode();
        System.out.println("Status Code:"+statuscode);
        Assert.assertEquals(statuscode, 401);
        System.out.println(response.getStatusLine());
        System.out.println(response.getBody().asString());
        
        if(response.getStatusCode()==401)
			
		{
	    	
			response  = httpRequest.request(Method.GET);

			Assert.assertTrue(response.getBody().asPrettyString().contains("timestamp"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("status"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("error"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("path"));
			
		}
	
        }
	@Test(dataProvider="AuthCredentials",dataProviderClass=DataProvider_LMS.class,priority=2)
	public void basic_authorization(String username,String password,String StatusCode) {
		//specify baseURI
	    RestAssured.baseURI = "https://lms-admin-rest-service.herokuapp.com/programs";
	    
	    //request object
    	httpRequest = RestAssured.given().auth().basic(username, password);
    	
    	//response object
        response = httpRequest.get();
        
        //status code validation
        int sc = Integer.parseInt(StatusCode);
        int statuscode = response.getStatusCode();
        
        System.out.println("Status Code is : "+response.getStatusCode());
        
        if(sc==200) {
        	Assert.assertEquals(statuscode, sc);
        	Response response1 = httpRequest.request(Method.GET);

			Assert.assertTrue(response.getBody().asPrettyString().contains("programId"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("programName"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("programDescription"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("online"));
        	
        }
        else {
        	Assert.assertEquals(statuscode,401);
        	
        }
        	
        	}
	
	@Test(priority=3)
	public void InvalidURL() {
		//specify baseURI
	    RestAssured.baseURI = "https://lms-admin-rest-service.herokuapp.com/programss1";
	    
	  //basic authentication
    	PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    	authScheme.setUserName("admin");
    	authScheme.setPassword("password");
    	RestAssured.authentication=authScheme;
	    
	   //request object
    	httpRequest = RestAssured.given();
    	
    	//response object
        response = httpRequest.get();
        System.out.println("Status Code is:"+response.getStatusCode());
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
     if(response.getStatusCode()==404)
			
		{
	    	
			response  = httpRequest.request(Method.GET);

			Assert.assertTrue(response.getBody().asPrettyString().contains("timestamp"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("status"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("error"));
			Assert.assertTrue(response.getBody().asPrettyString().contains("path"));
			
		}
        System.out.println("<------------------LOGIN Validations--------------->");
        
        }
	
}
