Feature: EdgeCase operations on Issue's API

Scenario Outline: EdgeCases on Issue API
	Given User calls "deleteIssueAPI" with "InvalidAccessToken"
	And API call failed "message" equals "401 Unauthorized"
	And User calls "deleteIssueAPI" with "ExpiredAccessToken"
	And API call failed "error" equals "invalid_token"
	And API call failed "error_description" equals "Token is expired. You can either do re-authorization or token refresh."
	And User calls "deleteIssueAPI" with "InvalidIssueID"
	And API call failed "message" equals "404 Issue Not Found"
	And User calls "newIssueAPI" without mandatory fields
	And User calls "editIssueAPI" without mandatory fields