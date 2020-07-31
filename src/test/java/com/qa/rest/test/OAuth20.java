package com.qa.rest.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth20 {

	public String testOauth20_generateAccessToken() {

		Response resp = RestAssured.given().formParam("client_id", "OAuth20_Pravin")
				.formParam("client_secret", "d25e93fa12c27ca839c92649a451ebfd")
				.formParam("grant_type", "client_credentials").post("http://coop.apps.symfonycasts.com/token");

		return resp.jsonPath().get("access_token");
	}

	// @Test
	public void testOauth20HardcodedToken() {

		Response resp = RestAssured.given().auth().oauth2("b695552b08b8a804b4d4d9884361ae5891176d75")
				.post("http://coop.apps.symfonycasts.com/api/780/chickens-feed");

		System.out.println("Response code : " + resp.getStatusCode());
		System.out.println("Response body : " + resp.getBody().asString());
	}

	@Test
	public void testOauth20DynamicToken() {

		Response resp = RestAssured.given().auth().oauth2(testOauth20_generateAccessToken())
				.post("http://coop.apps.symfonycasts.com/api/780/chickens-feed");

		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	@Test
	public void testOauth20NegativeTest() {

		Response resp = RestAssured.given().auth().oauth2(testOauth20_generateAccessToken())
				.post("http://coop.apps.symfonycasts.com/api/780/eggs-collect");

		Assert.assertEquals(resp.getStatusCode(), 401);
	}
}
