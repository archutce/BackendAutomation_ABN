############### ABN Backend Test Automation Assignment ################

-----------Used cucumber BDD framework to execute automate Issue's API functionality---------------------------------------------------------

Below is overview of framework created


1. Created a cucumber junit TestRunner.java  under src/test/java with folder cucumber.options which runs all the feature files and its corresponding step definitions.
2. Created a feature file CRUDIssue.feature under src/test/java/features which has two scenarios.
3. Created a step definition file CRUDStepdefinitions.java under src/test/java/stepDefinitions which has actual implementation.
4. Created config.properties under src/test/resources to drive the global variables.

5. Used two approaches for input test data 
	*** Used Apache POI API to get input data from excel, created TestData.xlsx under src/test/resources to store the input data.
	Created DataDriven.java which has actual implementation  to drive input data from excel based on sheet name and test case name.

	*** Used jackson databind jars to create input payload using POJO, Created package pojo under src/main/java for serialization.

6. Created TestDataBuild.java under src/test/resources which has creation of payload using two approach
	*** POJO
	*** HashMap (data comes from excel)

7.Logging
	*** Create a log file (logs.txt) to log all the request and response details at the project level (Request and Response Logging filter)
	*** Created log4j2.xml under src/main/java/resources to log the info or error details in the console

8. Created ReusableTestUtils.java under src/test/java/testUtils which has reusable methods like
	*** Getting value from property file
	*** RequestSpecification
	*** Response Specification
	*** Converting raw response to JSON

----------------------------------------------------------------------------------------------------------------------------------------------------
Issue API AUTOMATION

****** Scenarion 1 - CRUD Operations ******

Created a New Issue - Uses user_calls_new_issue_api_with_payload(String...) method
1. Payload created using pojo and data from feature files, used request and response specification, oauth2 token
2. Validating the status code and json response, check the details added are present in response
3. check additional attributes are present in response using api_call_success_equals()

Update the details of newly created issue - Uses user_calls_edit_issue_api_to_update_the_details() method
1.Payload created using hashmap and data from excel, used  request and response specification, oauth2 token, query params
2. Validating the status code and json response, check the details updated are present in response

Get Details of the newly created issue Uses user_calls_get_issue_api_to_retrive_details() method
1.Used  request and response specification, oauth2 token and validating the status code
2. If getIssue API called after NewIssueAPI - compare both API's response are same
2. If getIssue API called after EditIssueAPI - compare both API's response are same


Delete  newly created issue - Uses user_calls_delete_issue_api_to_delete_the_issue() method
1.Used  request specification, oauth2 token
2. Validating the status code, returns empty response validating the empty response

******* Scenario 2 - Edge Cases **********

1. User getIssueAPI called after DeletedIssueAPI ie with ID no more present - returns 404 Not found
2. User calls deleteIssueAPI with valid ID and Invalid access token - returns 401 unauthorized
3. User calls deleteIssueAPI with valid ID and Invalid access token - returns 401 token expiry error
4. User calls deleteIssueAPI with invalid issue  ID and  access token - returns 404 Issue Not Found
5. User calls newIssueAPI without mandatory fields - throws error response
6. User calls EditIssue API without mandatory fields - throws error response

----------------------------------------------------------------------------------------------------------------------------------------------



	

