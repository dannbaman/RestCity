import java.util.Scanner;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC001_Demo1_Get_City {
	
	@Test(priority = 1)
	public void  getCityData()
	{
		//base url
		RestAssured.baseURI = "https://restcountries.com/v3.1/capital/";
		
		//Request object
		RequestSpecification httprequest= RestAssured.given();
		
	    
		while(true)
	    {

			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			System.out.println("Enter city capital");

			String city = myObj.nextLine().toLowerCase();  // Read user input
			if (city.equals("quit"))
			{
				break;
			}
		    System.out.println("capital is: " + city);  // Output user input
		    
			//response object
			Response response = httprequest.request(Method.GET,city);
			
			//print response in console window
			String responseBody = response.getBody().asString();
			System.out.println("Response body is: "+ responseBody);
					//status code
			int statusCode = response.getStatusCode();
			System.out.println("status code is: "+ statusCode);
			Assert.assertEquals(statusCode, 200);
			//status line
			String statusLine= response.getStatusLine();
			System.out.println(statusLine);
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
			Assert.assertTrue(responseBody.contains("Russia"));
			
	    }
	}
	@Test(priority = 2)
	public void  getCityNegative()
	{
		String city = "limaa";
		//base url
		RestAssured.baseURI = "https://restcountries.com/v3.1/capital/";
				
		//Request object
		RequestSpecification httprequest= RestAssured.given();
		//response object
		Response response = httprequest.request(Method.GET,city);
		//print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: "+ responseBody);
		
		//status code
		int statusCode = response.getStatusCode();
		System.out.println("status code is: "+ statusCode);
		if(statusCode<=400) {
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		
	}
}
	@Test(priority = 3)
	public void  TestMoscow()
	{
		String city = "moscow";
		//base url
		RestAssured.baseURI = "https://restcountries.com/v3.1/capital/";
				
		//Request object
		RequestSpecification httprequest= RestAssured.given();
		//response object
		Response response = httprequest.request(Method.GET,city);
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("Russia"),true);
		JsonPath jsonpath=response.jsonPath();
		System.out.println(jsonpath.get("tld"));
		System.out.println(jsonpath.get("currencies[0]"));
		
	
}
	@Test(priority = 4)
	public void  RegisterPostMethod()
	{
		//base url
		RestAssured.baseURI = "https://restapi.com/customer";
				
		//Request object
		RequestSpecification httprequest= RestAssured.given();
		//Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Johnsss222");
		requestParams.put("LastName", "dsdsd");
		requestParams.put("UserName", "fdf");
		requestParams.put("Password", "adsd");
		requestParams.put("Email", "sdsds@gmail.com");
		httprequest.header("Content-Type","application/json");
		
		httprequest.body(requestParams.toJSONString());//ATTACH DATA to the request
		
		//Response object
		Response response = httprequest.request(Method.POST,"/register");
		
		//print response in console
		String responseBody = response.getBody().asString();
		System.out.println("response is " + responseBody);
		
		//status code validation
		int statusCode = response.getStatusCode();
		System.out.println("status code is " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		String successCode = response.jsonPath().get("SuccessCode");
		
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		
	
}
	@Test(priority = 5)
	public void  GetDemo()
	{
		String city = "moscow";
		//base url
		RestAssured.baseURI = "https://restcountries.com/v3.1/capital/";
				
		//Request object
		RequestSpecification httprequest= RestAssured.given();
		//response object
		Response response = httprequest.request(Method.GET,city);
		String responseBody = response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains("Russia"),true);
		
	
}
}
