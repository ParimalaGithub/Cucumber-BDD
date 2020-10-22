package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.Static;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClass.GMGetPlace;
import pojoClass.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	ResponseSpecification resspec;RequestSpecification res;
	Response response;  static String place_id;
	TestDataBuild TD = new TestDataBuild();
	@Given("Add place PayLoad with {string} {string} {string}")
	public void add_place_PayLoad_with(String name, String language, String address) throws Exception
	{
		
		GMGetPlace S = TD.AddPlacePayLoad(name, language, address);
				
		//resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res= given().spec(RequestSpec()).body((S));
		//throw new cucumber.api.PendingException();
	}				


@When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourcesAPI= APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("Post"))
			response= res.when().post(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("Delete"))
			response= res.when().delete(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("get"))
			response= res.when().get(resourcesAPI.getResource());
				}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	    //throw new cucumber.api.PendingException();
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		String actualValue=getJsonPath(response,keyValue);
		assertEquals(actualValue,expectedValue);
	    //throw new cucumber.api.PendingException();
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using_AddPlaceAPI(String expectedName,String getplaceapi) throws IOException {
		//Prepare requestSpec then add query parameter
		place_id=getJsonPath(response,"place_id");
		res=given().spec(RequestSpec()).queryParam("place_id",place_id);
		user_calls_with_http_request(getplaceapi,"get");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	   
	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		String S = TD.deletePlacePayLoad(place_id);
		res=given().spec(RequestSpec()).body(S);
	   	}
	
}
