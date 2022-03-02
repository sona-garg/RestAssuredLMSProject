package com.lmsApi.testCases;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

public class LMS_SchemaValidation {
	 @Test
		void getAuthentication() {
	    	given().auth()
	    	.basic("admin","password")
	    	.when()
	    	.get("https://lms-admin-rest-service.herokuapp.com/programs")
	    	.then()
	    	.assertThat()
	    	.statusCode(200)
	    	.body(matchesJsonSchemaInClasspath("schema.json"));
	 }
	 

}
