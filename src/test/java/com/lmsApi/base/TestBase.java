package com.lmsApi.base;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import com.lmsApi.utilities.Read_Propertyfile;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	 public static RequestSpecification httpRequest;
	 public static Response response;
	
	@BeforeClass
	public void setup(){
		
		Read_Propertyfile readprop = new Read_Propertyfile();
		Properties prop = readprop.loadProperties();
		RestAssured.baseURI= prop.getProperty("URL");
		httpRequest = RestAssured.given().auth().basic(prop.getProperty("Username"),prop.getProperty("password"));
		
		
	
	}
	
	

}
