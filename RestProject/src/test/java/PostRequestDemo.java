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

public class PostRequestDemo {

	@BeforeClass
	public void  setup()
	{

		RestAssured.baseURI="https://maps.googleapis.com";
		RestAssured.basePath="/maps/api";
		
	
	}
	@Test
	public void validateResponse() {
		given()
			.param("key", "AIzaSyBO-it6fhzzIJ_NmD2vA1asQ8XehFldqJA")
			.body("{"
					+ "\"location\": {"
					+ "\"lat\": -33.8669710,"
					+ "\"lng\": 151.1958750"
					+ "},"
					+ "\"accuracy\": 50,"
					+ "\"name\": \"Google Shoes!\","
					+ "\"phone_number\": \"(02) 9374 4000\","
					+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
					+ "\"types\": [\"shoe_store\"],"
					+ "\"website\": \"http://www.google.com.au/\","
					+ "\"language\": \"en-AU\""
					+ "}")
		.when()
			.post("/place/add/json")
		.then()
			.statusCode(200);
		
	}

	
}
