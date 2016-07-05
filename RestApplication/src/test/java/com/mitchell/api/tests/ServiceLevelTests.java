package com.mitchell.api.tests;

import javax.ws.rs.PathParam;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.ResponseBody;


public class ServiceLevelTests {

	
	private final String URI="http://localhost:8085/RestApplication/webapi/vehicles/";
	private final String inputPayload="{\"Id\": 24444,\"Year\": 2012,\"Make\": \"Ferrari\",\"Model\": \"F430\"}";
	private final String putPayload="{\"Id\": 24444,\"Year\": 1982,\"Make\": \"Polo\",\"Model\": \"F430\"}";
	private final String putPayloadWithNegativeData="{\"Id\": 1,\"Year\": 0,\"Make\": \"\",\"Model\": \"F430\"}";
	private final String badURI="http://localhost:8085/RestApplication/webapi/vehicle/";
	private RestAssured assured =null;
	
	
	@BeforeClass
	public void inialize() {
		assured = new RestAssured();
	}

	@Test(priority=1)
	public void testGetCall() {
		assured.expect().statusCode(200).when().get(URI);
	}
	@Test(priority=3)
	public void testGetCallByID() {
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8085/RestApplication/webapi/vehicles/24444").getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals("24444", jp.get("Id").toString());
		Assert.assertEquals("2012", jp.get("Year").toString());
		Assert.assertEquals("Ferrari", jp.get("Make"));
		Assert.assertEquals("F430", jp.get("Model"));
	}
	
	@Test(priority=2)
	public void testPostCall() throws Exception{
		assured.given().contentType("application/json").body(inputPayload).when(). post(URI).thenReturn();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8085/RestApplication/webapi/vehicles/24444").getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals("24444", jp.get("Id").toString());
		Assert.assertEquals("2012", jp.get("Year").toString());
		Assert.assertEquals("Ferrari", jp.get("Make"));
		Assert.assertEquals("F430", jp.get("Model"));
	}
	
	@Test(priority=4)
	public void testPutCall(){
		assured.given().contentType("application/json").body(putPayload).when(). put("http://localhost:8085/RestApplication/webapi/vehicles/24444").thenReturn();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8085/RestApplication/webapi/vehicles/24444").getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertEquals("24444", jp.get("Id").toString());
		Assert.assertEquals("1982", jp.get("Year").toString());
		Assert.assertEquals("Polo", jp.get("Make"));
		Assert.assertEquals("F430", jp.get("Model"));
				
	}
	
	@Test(priority=5)
	public void testDeleteCall(){
		assured.given().contentType("application/json").when(). delete("http://localhost:8085/RestApplication/webapi/vehicles/24444").thenReturn();
		ResponseBody response=assured.given().contentType("application/json"). when().
        get(URI).getBody();
		String json=response.asString();
		JsonPath jp = new JsonPath(json);
		Assert.assertNotEquals("24444", jp.get("Id").toString());
		Assert.assertNotEquals("1982", jp.get("Year").toString());
		Assert.assertNotEquals("Polo", jp.get("Make"));
		Assert.assertNotEquals("F430", jp.get("Model"));
				
	}
	
	@Test(priority=6)
	public void testErrorCodes(){
		assured.expect().statusCode(404).when().get(badURI);
	}
	
	@Test(priority=7)
	public void testGetIdWithZeroValue() {
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8085/RestApplication/webapi/vehicles/0").getBody();
		Assert.assertEquals("vehicle Id cannot be blank or negative", response.asString(),"Response message is not as expected for vehicle ID's with 0");
	}
	
	@Test(priority=8)
	public void testGetIdWithNegativeValue() {
		ResponseBody response=assured.given().contentType("application/json"). when().
        get("http://localhost:8085/RestApplication/webapi/vehicles/-1").getBody();
		Assert.assertEquals("vehicle Id cannot be blank or negative", response.asString(),"Response message is not as expected for vehicle ID's with 0");
	}
	
	@Test(priority=9)
	public void testPostCallWithNullObject() {
		ResponseBody response=assured.given().contentType("application/json").body("{}").when(). post(URI).thenReturn().body();
		Assert.assertEquals("vehicle Object should be entered", response.asString());
	}
	
	@Test(priority=9)
	public void testPutCallWithNullObject() {
		
		ResponseBody response=assured.given().contentType("application/json").body(putPayloadWithNegativeData).when(). put("http://localhost:8085/RestApplication/webapi/vehicles/1").thenReturn().body();
		Assert.assertEquals("valid vehicle Object and vehicle Id should be entered", response.asString());
	}
	
	@Test(priority=10)
	public void testDeleteCallWithNegativeValues() {
		ResponseBody response=assured.given().contentType("application/json").when(). delete("http://localhost:8085/RestApplication/webapi/vehicles/-1").thenReturn().body();
		Assert.assertEquals("valid vehicle Id should be given", response.asString());
	}
	
	
	
}

