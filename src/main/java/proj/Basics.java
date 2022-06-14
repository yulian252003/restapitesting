package proj;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.reUsableMethods;

public class Basics 
{

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		// validate if Add Place API  is working as expected
		//Given, when, there
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "quaclick123").header("Content-Type","application/json")
		//.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
		.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\julian.patino\\Documents\\jsonadd.json")))).when().post("maps/api/place/add/json")	
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		js.get("place_id");
		String placeId=js.get("place_id");
		System.out.println(placeId);
		
		//update address
		String newAddress = "calle 22-33-44-45";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"place_id\": \""+placeId+"\",\r\n"
				+ "    \"address\": \""+newAddress+"\",\r\n"
				+ "    \"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//Get place
		
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=reUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		//Add place->Update Place with New Address-> Get Place to validate if new address is present in response

	}

}
