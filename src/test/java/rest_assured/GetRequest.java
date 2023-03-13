package rest_assured;

import org.testng.Assert;
import org.testng.annotations.Test;

import config.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GetRequest {
//	String baseUrl="";
//	public void property() throws IOException {
//		FileReader reader=new FileReader("src/test/resources/values.properties");  
//	      
//	    Properties p=new Properties();  
//	    p.load(reader);  
//	      
//	    baseUrl=p.getProperty("baseurl");
//	}

	@Test
	public void getRequestExample1() throws IOException {
		Response response = RestAssured.get("https://api.github.com/users/Harikrishnan1106/repos");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		// System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		System.out.println(response.statusLine());
		System.out.println("Response Time: "+response.getTime()+"ms");
		System.out.println(response.jsonPath().getString("name"));
		System.out.println(response.jsonPath().getString("name[0]").equals("dicegame"));
		Assert.assertEquals(statusCode, 200);
		
		
	}
	@Test
	public void getRequestExample2() throws IOException {
		RestAssured.baseURI = "https://api.github.com/";
		RestAssured.given().when().get("/users/Harikrishnan1106/repos").then().statusCode(200).log().all();
		// System.out.println("Response Body: "+ response);
		System.out.println("2nd method");
		Response response = RestAssured.given().when().get("/users/Harikrishnan1106/repos").then()
		        .extract().response();
		String repoName="dicegame";
		JsonPath js = new JsonPath(response.asString());
		
		int size = js.getInt("name.size()");
		System.out.println("size is: "+size);
		
		for(int i = 0; i < size; i++)
		{
			if(js.getString("name["+i+"]").equals(repoName)) {
				System.out.println(repoName+" is available in the response");
				break;
			}
		}	
		
	}

}
