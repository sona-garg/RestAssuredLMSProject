package com.lmsApi.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lmsApi.base.TestBase;
import com.lmsApi.dataProvider.DataProvider_LMS;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class TC005_DeleteRequestLMS extends TestBase {
	@Test(dataProvider="Deleterequest",dataProviderClass=DataProvider_LMS.class,priority=1) 
	void delete_record(String ProgramID,String StatusCode) {
		 //send delete request
		Response response=httpRequest.request(Method.DELETE,ProgramID);
		
		
		System.out.println("Status code is: "+response.getStatusCode());
		
		int sc = Integer.parseInt(StatusCode);
		Assert.assertEquals(response.getStatusCode(), sc);
		
		//validating deleted Id is having null response body
				Response getresponse = httpRequest.request(Method.GET);
				if(getresponse.body()==null)
					Assert.assertEquals(response.body(),null);
				
				System.out.println("<-----------------DELETE Validations-------------->");
	
	}

}
