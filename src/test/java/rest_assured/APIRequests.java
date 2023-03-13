package rest_assured;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIRequests {
	@Test(priority = 1)
	public void getRequestExample1() throws IOException {
		Response response = RestAssured.get("https://api.github.com/users/Harikrishnan1106/repos");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		System.out.println(response.asPrettyString());
		System.out.println(response.statusLine());
		System.out.println(response.jsonPath().getString("name"));
		System.out.println(response.jsonPath().getString("name[0]").equals("dicegame"));
		Assert.assertEquals(statusCode, 200);	
	}
	@Test(priority = 2)
	public void getRequestExample2() {
		RestAssured.baseURI = "https://api.github.com/users/Harikrishnan1106/";
		RestAssured.given().when().get("/repos").then().statusCode(200).log().all();
		System.out.println("2nd method");
		RestAssured.given().when().get("/repos").then().statusCode(200).body("name[0]", equalTo("dicegame")).log()
				.all();
	}
	
	@Test(priority = 3)
	public void postRequestExample() {
		String baseUrl = "https://api.github.com";
	    String bearerToken = "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";
	    String endpoint = "/user/repos";
	    String repoName="RESTASSUREDAPI";
	    String payload = "{\"name\":\""+repoName+"\",\"description\":\"Demo Basic Rest Assured Repo\",\"homepage\":\"https://github.com\"}";
	    RestAssured.useRelaxedHTTPSValidation();
	    RestAssured.baseURI = baseUrl;
	    Response response = RestAssured
	      .given()
	        .header("Authorization", "Bearer " + bearerToken)
	        .header("Content-Type", "application/json")
	        .body(payload)
	      .when()
	        .post(endpoint)
	      .then()
	        .extract().response();
	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);
	    response.then()
	      .statusCode(201)
	      .assertThat()
	      .body("name", equalTo("DemoRestAssuredAPI12"));
	}
	@Test(priority = 4)
	public void deleteRequestExample() {
		String repoName="RESTASSUREDAPI";
		RestAssured.baseURI = "https://api.github.com";
		String bearerToken = "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";
		Response response = RestAssured.given().header("Authorization", "Bearer " + bearerToken).when()
				.delete("/repos/Harikrishnan1106/"+repoName).then().statusCode(204).extract().response();
		response.then().log().all();
		System.out.println(response.asString());
		System.out.println(response.statusCode());
	}
}
