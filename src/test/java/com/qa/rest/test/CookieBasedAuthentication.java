package com.qa.rest.test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CookieBasedAuthentication {

	public String getSessionID() {

		JSONObject json = new JSONObject();
		json.put("username", "pbaravk");
		json.put("password", "Praniti!29");

		Response resp = RestAssured.given().header("Content-Type", "application/json").body(json)
				.post("https://mtjira.intra.transformco.com/rest/auth/1/session");

		System.out.println("Status code : " + resp.getStatusCode());
		System.out.println("Response bode: " + resp.getBody().jsonPath().prettify());
		String sessionId = resp.getCookie("JSESSIONID");
		
		Response resp1 = RestAssured.given().contentType(ContentType.JSON).post("https://mtjira.intra.transformco.com/issue/createmeta/BOF/issuetypes/");
		System.out.println(resp1.getBody().asString());
		
		return sessionId;

	}
	
	@Test
	public void createIssue() {
		
		Response resp = RestAssured.given().
				contentType(ContentType.JSON).
				cookie("JSESSIONID", getSessionID()).
				body("{\"fields\":{\"project\":{\"key\":\"BOF\"},\"summary\":\"Test Jira example\",\"description\":\"Create a sample jira.\",\"issuetype\":{\"name\":\"Story\"},\"customfield_10477\":1}}").
				post("https://mtjira.intra.transformco.com/rest/api/2/issue/");
		//"{\"fields\":{\"project\":{\"key\":\"BOF\"},\"summary\":\"Test Jira example\",\"description\":\"Create a sample jira.\",\"issuetype\":{\"name\":\"Story\"},\"components\" : [{\"add\" : {\"name\" : \"CCIL\"}}],\"customfield_10477\":null}}"

		System.out.println("Status code : " + resp.getStatusCode());
		System.out.println("Response body: " + resp.getBody().jsonPath().prettify());
		
		
	}

}
