package com.lmsApi.testCases;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.lmsApi.base.*;
import com.lmsApi.dataProvider.DataProvider_LMS;

public class TC002_GetRequestLMS extends TestBase {
	 @Test()
		void get_AllPrograms() {
	    	//status code validations
		    response = httpRequest.request(Method.GET);
	    	int statusCode=response.getStatusCode();
	    	System.out.println("The status code is:"+statusCode);
		    Assert.assertEquals(statusCode, 200);
		    System.out.println("<--------------End of Get request---------------->");
		    if(response.getStatusCode()==200)
				
			{
		    	Response response1 = httpRequest.request(Method.GET);

				Assert.assertTrue(response.getBody().asPrettyString().contains("programId"));
				Assert.assertTrue(response.getBody().asPrettyString().contains("programName"));
				Assert.assertTrue(response.getBody().asPrettyString().contains("programDescription"));
				Assert.assertTrue(response.getBody().asPrettyString().contains("online"));
				}
	    	
	    	//print response in console
	    	//System.out.println("Response body is:  "+response.getBody().asPrettyString());
	    	}
	@Test(dataProvider="ProgramID",dataProviderClass=DataProvider_LMS.class,priority=1) 
	void get_ProgramID(String ProgramID,String StatusCode) {
		
		response= httpRequest.request(Method.GET,ProgramID);
		String responseBody= response.getBody().asPrettyString();
		System.out.println("Statuc code is: "+response.getStatusCode());
		System.out.println("Response Body is: "+response.getBody().asPrettyString());
		//System.out.println("Response Body is: "+responseBody);
		int sc = Integer.parseInt(StatusCode);
		Assert.assertEquals(response.getStatusCode(), sc);
		
		
		
		if(response.getStatusCode()==200)
				
			{
			 given()
			    //.header("content-Type", "application/json")
			   // .params(key,value)
			    .auth()
			    .basic("admin","password")
			    .when()
			    	.get(" https://lms-admin-rest-service.herokuapp.com/programs/")
			    //.get("https://jobs123.herokuapp.com/Jobs")
			    .then()
			    .statusCode(200)
			   //.assertThat().body("data.'Job Title'",hasItems("SDET"));
			    
			    .assertThat().body("programId",hasItem(7575));
			    //.assertThat().body("programName",hasItems("SDET"));
			 
		    	
				response  = httpRequest.request(Method.GET);

				Assert.assertTrue(response.getBody().asPrettyString().contains("programId"));
				Assert.assertTrue(response.getBody().asPrettyString().contains("programName"));
				Assert.assertTrue(response.getBody().asPrettyString().contains("programDescription"));
				Assert.assertTrue(response.getBody().asPrettyString().contains("online"));
				
			}
		// System.out.println("<-----------------GET Validations-------------->");
	}

}