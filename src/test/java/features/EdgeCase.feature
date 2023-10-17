Feature: Edge Cases on Issue's API

Scenario Outline: Edge Cases on Issue API
	Given	User calls "newIssueAPI" without mandatory fields
	And 	User calls "deleteIssueAPI" with "InvalidAccessToken"
	And 	User calls "deleteIssueAPI" with "ExpiredAccessToken"
	And 	User calls "editIssueAPI" without mandatory fields