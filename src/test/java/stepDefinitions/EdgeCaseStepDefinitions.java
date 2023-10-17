package stepDefinitions;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testUtils.ResuableTestUtils;
import io.restassured.specification.RequestSpecification;
import resources.TestDataBuild;
import org.apache.logging.log4j.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EdgeCaseStepDefinitions extends ResuableTestUtils {

	RequestSpecification reqGiven;
	Response actualCreateResponse,actualGetResponse,actualEditResponse,actualDeleteResponse;
	String createIssueResponse, getIssueResponse,editIssueResponse,deleteIssueResponse;
	JsonPath js;
	String id;

	HashMap<String, Object> deleteMap;
	CRUDStepDefinitions crud= new CRUDStepDefinitions();

	TestDataBuild data = new TestDataBuild();
	private static Logger log=LogManager.getLogger(EdgeCaseStepDefinitions.class.getName());
	ObjectMapper objectMapper = new ObjectMapper();
	
	//validate invalid Token,expired Token, invalid Issue ID
	
	@And ("User calls {string} with {string}")
	public void user_calls_with(String api, String condition) throws IOException {
		
		//calls NewIssue API to get a issue ID and performs further steps
		id=crud.user_calls_new_issue_api_with_payload("title 4", "description 4", "1234", "regression", "issue");
		
	  if(condition.equalsIgnoreCase("InvalidAccessToken")) {
		  deleteIssueResponse = given().spec(requestSpecification())
					.pathParam("issueId", id).auth().oauth2(getGlobalPropertiesValue("invalidAccessToken"))
					.when().delete("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.statusCode(401).extract().response().asString(); 
		  
		  log.info("Delete Issue Response with InvalidAccessToken " + deleteIssueResponse);
		  
		  deleteMap = objectMapper.readValue(deleteIssueResponse, HashMap.class);
		  log.info(deleteMap.get("message"));
		  
		  
	  }
	  if(condition.equalsIgnoreCase("ExpiredAccessToken")) {
		  deleteIssueResponse = given().spec(requestSpecification())
					.pathParam("issueId", id).auth().oauth2(getGlobalPropertiesValue("expiredAccessToken"))
					.when().delete("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.statusCode(401).extract().response().asString();
		  log.info("Delete Issue Response with ExpiredAccessToken " + deleteIssueResponse);
		  deleteMap = objectMapper.readValue(deleteIssueResponse, HashMap.class);
		  log.info(deleteMap.get("error"));
		  log.info(deleteMap.get("error_description"));
		 
	  } 
	  if(condition.equalsIgnoreCase("InvalidIssueID")) {
		  deleteIssueResponse = given().spec(requestSpecification())
					.pathParam("issueId",data.inValidIssueId ).auth().oauth2(getGlobalPropertiesValue("accessToken"))
					.when().delete("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.statusCode(404).extract().response().asString();
		  log.info("Delete Issue Response with ExpiredAccessToken " + deleteIssueResponse);
		  deleteMap = objectMapper.readValue(deleteIssueResponse, HashMap.class);
		  log.info(deleteMap.get("error"));
		  log.info(deleteMap.get("error_description"));
		 
	  } 
	  
	  
	}
	
	  //validating error response
	  @Given("API call failed {string} equals {string}")
	  public void api_call_failed_equals(String key, String value) {
	      assertEquals(deleteMap.get(key),value);
	  }
	  
	  
	//Validating the mandatory checks
	
	@Given("User calls {string} without mandatory fields")
	public void user_calls_without_mandatory_fields(String api) throws IOException {
	    
		//Create New Issue API without mandatory fields returns 400 with error title is missing
		if(api.equalsIgnoreCase("newIssueAPI")) {
	    	
	    	log.info("calling newIssue API without mandatory fields");
	    	createIssueResponse = given().spec(requestSpecification())
					.auth().oauth2(getGlobalPropertiesValue("accessToken"))
					.body(data.mandatoryRequest())
					.when().post("/projects/{projectId}/issues").then().spec(responseSpecification())
					.statusCode(400).extract().response().asString();

			log.info("create Issue Error Response"+createIssueResponse);
			js = ResuableTestUtils.rawToJson(createIssueResponse);
			assertEquals(data.errorValue,js.getString("error"));
			
	    }
		
		//Edit New Issue API without mandatory fields returns 400 with parameters needed
	    if(api.equalsIgnoreCase("editIssueAPI")) {
	    	log.info("calling editIssue API without mandatory fields");
	    	editIssueResponse = given().spec(requestSpecification())
					.pathParam("issueId", id)
					.auth().oauth2(getGlobalPropertiesValue("accessToken"))
					.body(data.mandatoryRequest())
					.when().put("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.statusCode(400).extract().response().asString();
	    	
	    	log.info("edit Issue Error Response"+editIssueResponse);
	    	js=rawToJson(editIssueResponse);
	    	
	    	//Convert JSON response as String and get the parameters needed using streams
	    	
	    	String errorMessage=js.getString("error");
	    	log.info("edit Issue Error message"+errorMessage);
	    	
	    	Stream.of(errorMessage.split(","))
	    	  .map(String::trim)
	    	  .map (e -> new String(e))
	    	  .forEach(e->log.info(e));
	    }
	    }
	

}
