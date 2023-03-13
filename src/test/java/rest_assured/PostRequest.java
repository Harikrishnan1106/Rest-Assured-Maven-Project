package rest_assured;

import org.hamcrest.core.StringContains;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class PostRequest {

	@Test
	public void postRequestExample() throws FileNotFoundException, IOException {
		String baseUrl = "https://api.github.com";
	    String bearerToken = "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";
	    String endpoint = "/user/repos";
	    String repoName="RESTASSUREDAPI";
	    String payload = "{\"name\":\""+repoName+"\",\"description\":\"Demo Basic Rest Assured Repo\",\"homepage\":\"https://github.com\"}";
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
	    String json = response.asString();
	    try (FileOutputStream fos = new FileOutputStream(new File("test-output/postresult.json"))) {
	        fos.write(json.getBytes());
	    }
	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);
		
	    response.then()
	      .statusCode(201)
	      .assertThat()
	      .body("name", equalTo("RESTASSUREDAPI"));
	}
}
