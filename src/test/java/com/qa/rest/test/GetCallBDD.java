package com.qa.rest.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import javax.print.attribute.ResolutionSyntax;

public class GetCallBDD {

	//@Test
	public void testNumberOfCircuits2017Seaon() {
		given().
		when().
				get("http://ergast.com/api/f1/2017/circuits.json").
		then().
			assertThat().
			statusCode(200).
			and().
			body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
			and().
			header("Content-Length", equalTo("4551")).
			and().
			cookie("", "");
	}
	
	//@Test
	public void testNumberOfCircuits2017Seaon1() {
		Response resp = get("http://ergast.com/api/f1/2017/circuits.json");
		System.out.println("Response code : " + resp.getStatusCode());
		System.out.println("Response time : " + resp.getTime());
		
		String body = resp.asString();
		System.out.println("Body : " + body);
		System.out.println(resp.getSessionId());
		System.out.println(resp.getTimeIn(TimeUnit.MILLISECONDS));
	
		Assert.assertEquals(resp.getStatusCode(), 200);
		
	}
}
