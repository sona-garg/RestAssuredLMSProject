package com.lmsApi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lmsApi.base.TestBase;
import com.lmsApi.dataProvider.DataProvider_LMS;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC004_PutRequestLMS extends TestBase{
	@Test(dataProvider="PUTrequest",dataProviderClass=DataProvider_LMS.class,priority=1) 
	void put_record(String ProgramID,String ProgramName,String ProgramDesc,String Online,String StatusCode) {
		
		boolean XL_Online = Boolean.parseBoolean(Online);
		
		//we created data which we can send along with post request
				JSONObject requestParams = new JSONObject();
				requestParams.put("online", XL_Online);
				requestParams.put("programDescription", ProgramDesc);
				requestParams.put("programName", ProgramName);
				//requestParams.put("programId", ProgramID);
				
		//Added header specifying the type of data
				httpRequest.header("Content-Type","application/json");
				
		//Add the json to the body of the request
				httpRequest.body(requestParams.toJSONString());
				
	    //send post request
				Response response=httpRequest.request(Method.PUT,ProgramID);
				
				int sc = Integer.parseInt(StatusCode);
				System.out.println("Status Code is: "+sc);
				Assert.assertEquals(response.getStatusCode(), sc);
				
				System.out.println("Response Body is: "+response.getBody().asPrettyString());
				
				//JSON Representation form JSON String
				JsonPath jsonpath = JsonPath.from(response.getBody().asString());
				
				//validation after updating record
				if(sc==200) {
					String program_name=jsonpath.getString("programName");
					String program_desc=jsonpath.getString("programDescription");
					boolean online = jsonpath.getBoolean("online");
					
					System.out.println("The program name is: "+program_name);
					System.out.println("The program descriotion is: "+program_desc);
					System.out.println("The online value is: "+online);
					
					Assert.assertEquals(program_name,ProgramName );
					Assert.assertEquals(program_desc,ProgramDesc);
					Assert.assertEquals(online, XL_Online);
					
					
					
				}
				System.out.println("<-----------------PUT Validations-------------->");
	}

}
