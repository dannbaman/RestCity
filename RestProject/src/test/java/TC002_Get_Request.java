import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Request {
		@Test(priority = 1)
		public void  googleMapTest()
		{
			//base url
			RestAssured.baseURI = "https://maps.googleapis.com";
			
			//Request object
			RequestSpecification httprequest= RestAssured.given();
				    
			//response object
			Response response = httprequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBO-it6fhzzIJ_NmD2vA1asQ8XehFldqJA");
				
			//print response in console window
			String responseBody = response.getBody().asString();
			//System.out.println("Response body is: "+ responseBody);
			//status code
			int statusCode = response.getStatusCode();
			System.out.println("status code is: "+ statusCode);
			Assert.assertEquals(statusCode, 200);
			//status line
			String statusLine= response.getStatusLine();
			System.out.println(statusLine);
			
			String contentType = response.header("Content-Encoding");
			System.out.println("hi");
			
		    }
		}
