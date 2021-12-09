import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getDemo {

	@BeforeClass
	public void  setup()
	{

		RestAssured.baseURI="https://maps.googleapis.com";
		RestAssured.basePath="/maps/api";
		
	
	}
	@Test
	public void statusCodeVerification() {
		given()
			.param("units", "imperial")
			.param("origins", "Washington,DC")
			.param("destinations", "New+York+City,NY")
			.param("key", "AIzaSyBO-it6fhzzIJ_NmD2vA1asQ8XehFldqJA")
		.when()
			.get("/distancematrix/json")
		.then()
			.statusCode(200);
	}
	@Test
	public void getResponseBody() {
		Response res = (Response) given()
			.param("units", "imperial")
			.param("origins", "Washington,DC")
			.param("destinations", "New+York+City,NY")
			.param("key", "AIzaSyBO-it6fhzzIJ_NmD2vA1asQ8XehFldqJA")
		.when()
			.get("/distancematrix/json");
		System.out.println(res.body().asString());
	
	}
	@Test
	public void validateResponse() {
		given()
			.param("units", "imperial")
			.param("origins", "Washington,DC")
			.param("destinations", "New+York+City,NY")
			.param("key", "AIzaSyBO-it6fhzzIJ_NmD2vA1asQ8XehFldqJA")
		.when()
			.get("/distancematrix/json")
		.then()
			.statusCode(200)
			.and()
			.body("rows[0].elements[0].distance.text", equalTo("228 mi"))
			.contentType(ContentType.JSON);
	}

	
}
