package stepDefinitions;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testUtils.ResuableTestUtils;
import io.restassured.specification.RequestSpecification;
import resources.TestDataBuild;
import org.apache.logging.log4j.*;

public class EdgeCaseStepDefinitions extends ResuableTestUtils {
	
	RequestSpecification reqGiven;
	Response actualCreateResponse,actualGetResponse,actualEditResponse,actualDeleteResponse;
	String createIssueResponse, getIssueResponse,editIssueResponse,deleteIssueResponse;
	JsonPath js;
	int id=64;
	
	TestDataBuild data = new TestDataBuild();
	private static Logger log=LogManager.getLogger(CRUDStepDefinitions.class.getName());
	
	@Given("User calls newIssueAI with {string}")
	public void user_calls_new_issue_ai_with(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@Given("User calls {string} with {string}")
	public void user_calls_with(String api, String condition) throws IOException {
	
	  if(condition.equalsIgnoreCase("InvalidAccessToken")) {
		  actualDeleteResponse = given().spec(requestSpecification())
					.pathParam("issueId", id).auth().oauth2(getGlobalPropertiesValue("invalidAccessToken"))
					.when().delete("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.statusCode(401).extract().response();			
	  }
	  if(condition.equalsIgnoreCase("ExpiredAccessToken")) {
		  actualDeleteResponse = given().spec(requestSpecification())
					.pathParam("issueId", id).auth().oauth2(getGlobalPropertiesValue("expiredAccessToken"))
					.when().delete("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.statusCode(401).extract().response();
	  }
	  deleteIssueResponse=actualDeleteResponse.asString();
		log.info("Delete Issue Response" + deleteIssueResponse); 
	}
	
	@Given("User calls {string} without mandatory fields")
	public void user_calls_without_mandatory_fields(String api) throws IOException {
	    if(api=="newIssueAPI") {
	    	
	    	log.info("into newIssueAPI----");
	    	createIssueResponse = given().spec(requestSpecification())
					.auth().oauth2(getGlobalPropertiesValue("accessToken"))
					.body("{\r\n"
							+ "  \"titl\": \"3rd Issue Title\"\r\n"
							+ "}")
					.when().post("/projects/{projectId}/issues").then().spec(responseSpecification())
					.statusCode(400).extract().response().asString();

			log.info("Create New Issue Response"+createIssueResponse);
	    }}

}
