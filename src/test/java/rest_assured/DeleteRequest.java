package rest_assured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteRequest {

	@Test
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
