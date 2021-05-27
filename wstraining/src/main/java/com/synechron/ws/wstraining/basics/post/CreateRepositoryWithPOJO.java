package com.synechron.ws.wstraining.basics.post;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.synechron.ws.wstraining.basics.pojo.CreateRepoPOJO;

import io.restassured.RestAssured;

public class CreateRepositoryWithPOJO {

	
	String baseurl = "https://api.github.com";
	String barrierToken = "Bearer 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";
	
	@Test
	public void validateCreateRepository()
	{
				
		RestAssured.baseURI = baseurl;
		CreateRepoPOJO cp = new CreateRepoPOJO();
		Faker f = new Faker();
		String repoName = f.name().firstName();
		cp.setName(repoName);
		cp.setDesc(f.internet().emailAddress());
		
		RestAssured.given()
						.headers("Authorization", barrierToken)
						.headers("Content-type", "application/json")
						.body(cp)
						.when()
							.post("/user/repos")
						.then()
							.assertThat().statusCode(201).and()
							.body("name", CoreMatchers.equalTo(repoName)).log().all();
	}
}