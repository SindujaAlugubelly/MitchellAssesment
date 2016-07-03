package com.mitchell.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.ResponseBody;




public class ServiceLevelTests {
	
	
	private final String URI="http://localhost:8080/RestApplication/webapi/vehicles/";
	private final String inputPayload="{\"Id\": 24444,\"Year\": 2012,\"Make\": \"Ferrari\",\"Model\": \"F430\"}";
	private final String putPayload="{\"Id\": 24444,\"Year\": 1982,\"Make\": \"Polo\",\"Model\": \"F430\"}";

	@Test(priority=1)
	public void testGetCall() {
		RestAssured assured = new RestAssured();
		assured.expect().statusCode(200).when().get(URI);
	}
	@Test(priority=2)
	public void testGetCallByID() {
		RestAssured assured = new RestAssured();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8080/RestApplication/webapi/vehicles/3").getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals("3", jp.get("Id").toString());
		Assert.assertEquals("1989", jp.get("Year").toString());
		Assert.assertEquals("Mercedes", jp.get("Make"));
		Assert.assertEquals("Benz 300SL", jp.get("Model"));
	}
	
	@Test(priority=3)
	public void testPostCall() throws Exception{
		RestAssured assured = new RestAssured();
		assured.given().contentType("application/json").body(inputPayload).when(). post(URI).thenReturn();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8080/RestApplication/webapi/vehicles/24444").getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals("24444", jp.get("Id").toString());
		Assert.assertEquals("2012", jp.get("Year").toString());
		Assert.assertEquals("Ferrari", jp.get("Make"));
		Assert.assertEquals("F430", jp.get("Model"));
	}
	
	@Test(priority=4)
	public void testPutCall(){
		RestAssured assured = new RestAssured();
		assured.given().contentType("application/json").body(putPayload).when(). put("http://localhost:8080/RestApplication/webapi/vehicles/24444").thenReturn();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8080/RestApplication/webapi/vehicles/24444").getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals("24444", jp.get("Id").toString());
		Assert.assertEquals("1982", jp.get("Year").toString());
		Assert.assertEquals("Polo", jp.get("Make"));
		Assert.assertEquals("F430", jp.get("Model"));
				
	}
	
	@Test(priority=5)
	public void testDeleteCall(){
		RestAssured assured = new RestAssured();
		assured.given().contentType("application/json").when(). delete("http://localhost:8080/RestApplication/webapi/vehicles/24444").thenReturn();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get(URI).getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertNotEquals("24444", jp.get("Id").toString());
		Assert.assertNotEquals("1982", jp.get("Year").toString());
		Assert.assertNotEquals("Polo", jp.get("Make"));
		Assert.assertNotEquals("F430", jp.get("Model"));
				
	}
	
	
}

