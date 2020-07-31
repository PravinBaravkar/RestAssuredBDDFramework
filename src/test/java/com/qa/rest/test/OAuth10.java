package com.qa.rest.test;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class OAuth10 {

	//@Test
	public void postTweet() {
		
		Response resp = given().
		auth().
		oauth("8bb8813b37dY577LSmQrQL4Go", "xtkhV978pfMC5CJZG7szu52jpmyVdeovo11mjYRNXTSNKIMsEy", "359839584-Lpd5RntXtK1dA9tFUxdBFKwUFlIxkdv13TMyNIWR", "8bwyBcfEg1OU8eHVo9Ju98OHeR0sijvpNEDIcIkRSCKnr").
		post("https://api.twitter.com/1.1/statuses/update.json?status=hello");
		
		System.out.println("Status code : " + resp.getStatusLine());
		//System.out.println("Response body : " + resp.asString());
		System.out.println("Response body : " + resp.jsonPath().prettify());
		
		JsonPath json = resp.jsonPath();
		
		String tweetId = json.get("id_str");
		
		System.out.println("My tweet id id : " + tweetId);
	}
	
	@Test
	public void deleteTweet() {
		
		Response resp = given().
				auth().
				oauth("8bb8813b37dY577LSmQrQL4Go", "xtkhV978pfMC5CJZG7szu52jpmyVdeovo11mjYRNXTSNKIMsEy", "359839584-Lpd5RntXtK1dA9tFUxdBFKwUFlIxkdv13TMyNIWR", "8bwyBcfEg1OU8eHVo9Ju98OHeR0sijvpNEDIcIkRSCKnr").
				post("https://api.twitter.com/1.1/statuses/destroy/1230462257651281921.json");
		
		System.out.println("Status code : " + resp.getStatusLine());
		System.out.println("Response body : " + resp.jsonPath().prettify());
	}
}
