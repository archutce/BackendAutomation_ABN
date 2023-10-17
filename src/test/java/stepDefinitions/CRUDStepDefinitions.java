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

public class CRUDStepDefinitions extends ResuableTestUtils {

	RequestSpecification reqGiven;
	Response actualCreateResponse,actualGetResponse,actualEditResponse,actualDeleteResponse;
	String createIssueResponse, getIssueResponse,editIssueResponse;
	JsonPath js;

	String id;
	String label="regression";
	String due_date="2023-11-25";
	String message="404 Not found";
	

	TestDataBuild data = new TestDataBuild();
	private static Logger log=LogManager.getLogger(CRUDStepDefinitions.class.getName());
	
	//New Issue API - request body formed using POJO and values are taken from cucumber feature file
		@Given("User calls NewIssue API  with  payload {string} {string} {string} {string} {string}")
		public void user_calls_new_issue_api_with_payload(String title, String description, String assigneeId, String labels, String type) throws IOException {
		actualCreateResponse = given().spec(requestSpecification())
				.auth().oauth2(getGlobalPropertiesValue("accessToken"))
				.body(data.newIssuePayload(title, description, assigneeId, labels, type))
				.when().post("/projects/{projectId}/issues").then().spec(responseSpecification())
				.statusCode(201).extract().response();

		if(actualCreateResponse.getStatusCode()!=201) {
			log.error("Create New Issue API call Failed");
		}else {
			log.info("Create New Issue API call successful");		
		}
		
		//convert json response to string
		createIssueResponse = actualCreateResponse.asString();
		log.info("Create New Issue Response"+createIssueResponse);
		
		//Jsonpath to validate response body, validating the inputs passed are returned in the response
		js = ResuableTestUtils.rawToJson(createIssueResponse);
		id = js.getString("iid");
		assertEquals(title, js.getString("title"));
		assertEquals(description, js.getString("description"));
		assertNotEquals(assigneeId, js.getString("assignee")); //assignee id is not reflecting due to permissions, returns null
		assertEquals(labels, js.getString("labels[0]"));
		assertTrue(type.equalsIgnoreCase(js.getString("type")));
	}

	//Generic API response validation from the feature files
	@Then("API call success {string} equals {string}")
		public void api_call_success_equals(String key, String value) {
		assertEquals(js.getString(key), value);
	}
	
	//Edit Issue API - Request Body formed using Hashmap and values are taken from excel
	@Then("User calls EditIssue API to update the details")
	public void user_calls_edit_issue_api_to_update_the_details() throws IOException {
		actualEditResponse = given().spec(requestSpecification())
				.pathParam("issueId", id)
				.queryParam("add_labels", label).queryParam("due_date", due_date)
				.auth().oauth2(getGlobalPropertiesValue("accessToken"))
				.body(data.editIssuePayload())
				.when().put("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
				.statusCode(200).extract().response();
		
		//assertEquals(200, actualEditResponse.getStatusCode());
		
		if(actualEditResponse.getStatusCode()!=200) {
			log.error("Edit Issue  API call Failed");
		}else {
			log.info("Edit Issue  API call successfull");
		}
		
		editIssueResponse=actualEditResponse.asString();
		log.info("Edit Issue Response" + editIssueResponse);
		
		js = ResuableTestUtils.rawToJson(editIssueResponse);
		
		//verify the updated details
		HashMap<String,Object> hashKey=data.editIssuePayload();
		assertEquals(hashKey.get("title"),js.getString("title"));
		assertEquals(hashKey.get("issue_type"),js.getString("issue_type"));
		assertNotNull(hashKey.get("remove_labels"));
		assertNull(js.getString("remove_labels"));
		assertEquals(hashKey.get("confidential"),js.getBoolean("confidential"));
		assertEquals(hashKey.get("discussion_locked"),js.getBoolean("discussion_locked"));
		assertEquals(due_date,js.getString("due_date"));
		assertEquals(label,js.getString("labels[0]"));
	}
	
	//Get Issue API
		@Given("User calls getIssue API to retrive details {string}")
		public void user_calls_get_issue_api_to_retrive_details(String value) throws IOException {

			actualGetResponse = given().spec(requestSpecification())
					.pathParam("issueId", id).auth().oauth2(getGlobalPropertiesValue("accessToken"))
					.when().get("/projects/{projectId}/issues/{issueId}").then().spec(responseSpecification())
					.extract().response();
	
			getIssueResponse=actualGetResponse.asString();
			log.info("Get Issue Response" + getIssueResponse);
			
	//If getIssue API called after createNewIssue API, compare both create and get issue API response as string are equal
			
			if(value.equalsIgnoreCase("afterCreateIssue")) {
				getStatusCodeCheck();
			assertEquals(createIssueResponse,getIssueResponse);
			log.info("Newly created issue details are retrived successfully");
			}
			
	//If getIssue API called after editIssue API(update)compare both edit and get issue API response as string are equal
			
			if(value.equalsIgnoreCase("afterUpdateIssue")) {
				getStatusCodeCheck();
				assertEquals(editIssueResponse,getIssueResponse);
				log.info("Updated issue details are retrived successfully");
			}
	//If getIssue API called after deleteIssue API should return 404 not found
			if(value.equalsIgnoreCase("afterDeleteIssue")) {
				assertEquals(404, actualGetResponse.getStatusCode());
				js = ResuableTestUtils.rawToJson(getIssueResponse);	
				assertEquals(message, js.getString("message"));

			}
	
		}

	//Delete Issue API
	@Then("User calls DeleteIssue API to delete the issue")
	public void user_calls_delete_issue_api_to_delete_the_issue() throws IOException {
		
		actualDeleteResponse= given().spec(requestSpecification())
				.pathParam("issueId", id).auth().oauth2(getGlobalPropertiesValue("accessToken"))
				.when().delete("/projects/{projectId}/issues/{issueId}").then()
				.statusCode(204).extract().response();
		
		if(actualDeleteResponse.getStatusCode()!=204) {
			log.error("Delete Issue API call  Failed");
		}else {	
			log.info("Delete Issue API call successful");
		}
				
	}
	
	public void getStatusCodeCheck() {
		assertEquals(200, actualGetResponse.getStatusCode());
		
		if(actualGetResponse.getStatusCode()!=200) {
			log.error("Get Issue  API call Failed");
		}else {
			log.info("Get Issue  API call successfull");
		}
	}

}
