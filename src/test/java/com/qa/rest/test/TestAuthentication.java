package com.qa.rest.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestAuthentication {

	@Test
	public void testAuthentication() {
		Response resp = given().
				auth().
				preemptive().
				basic("ToolsQA", "TestPassword").
				when().get("http://restapi.demoqa.com/authentication/checkForAuthentication");
		
		System.out.println("response code from server is : " + resp.getStatusCode());
		
	
	}
}
