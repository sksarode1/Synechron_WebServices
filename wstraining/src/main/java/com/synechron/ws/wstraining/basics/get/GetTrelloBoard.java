package com.synechron.ws.wstraining.basics.get;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetTrelloBoard {
	
	public String baseurl = "https://api.trello.com";
	public String key = "c5f676759b86029624f7dcb31ccf655e";
	public String token = "9b60bd7325defc221aa0203822765f426ecb134adcf46f7ca823c569ced3a2f3";
			
	
	@Test
	public void validateGetBoardInBDDStyle()
	{
		RestAssured.
			given()
				.param("key", "c5f676759b86029624f7dcb31ccf655e")
				.param("token", "9b60bd7325defc221aa0203822765f426ecb134adcf46f7ca823c569ced3a2f3")
			.when()
				.get("https://api.trello.com/1/boards/llt4bIbC")
			.then()
				.assertThat().statusCode(200);
	}
	
	@Test
	public void validateGetBoardInBDDStyleSimplified()
	{
		RestAssured.baseURI = baseurl;
		
		RestAssured.
			given()
				.param("key", key)
				.param("token", token)
			.when()
				.get("/1/boards/llt4bIbC")
			.then()
				.assertThat().statusCode(200);
	}
	
	@Test
	public void validateGetBoardInRestFormat()
	{
		RestAssured.baseURI = baseurl;
		
		RequestSpecification reqSpecification = RestAssured.given();
		reqSpecification.param("key", key);
		reqSpecification.param("token", token);
			
		Response response = reqSpecification.get("/1/boards/llt4bIbC");
		ValidatableResponse valRes = response.then();
		valRes.assertThat().statusCode(200);
	}
	
	@Test
	public void validateGetBoardInExpectFormat()
	{
		RestAssured.baseURI = baseurl;
		
		RestAssured.
			given()
				.param("key", key)
				.param("token", token)
			.expect().statusCode(200).
			when().get("/1/boards/llt4bIbC");
	}

}
