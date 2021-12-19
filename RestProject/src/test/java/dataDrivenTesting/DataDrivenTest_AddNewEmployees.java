package dataDrivenTesting;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {

	@Test(dataProvider="emptdataprovider")
	void postAddNewEmployees(String name,String salary,String age)

	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		
		//we created data which we can send with post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", name);
		requestParams.put("salary", salary);
		requestParams.put("age", age);
		
		//Add a header stating the request body is json
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		//Post Request
		Response reponse = httpRequest.request(Method.POST,"/create");
		
		//capture response body to perform validations
		String responseBody = reponse.getBody().asString();
		Assert.assertEquals(responseBody.contains(name),true);
		Assert.assertEquals(responseBody.contains(salary),true);
		Assert.assertEquals(responseBody.contains(age),true);
		
		int statusCode = reponse.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
	}

	@DataProvider(name="emptdataprovider")
	 String [][] getEmpData()
	{
		String empdata[][]= {{"abc123","3000","40"},{"dan","3000","20"},{"dddd","80000","10"}};
		return(empdata);
		}
	}


