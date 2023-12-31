Feature: CRUD operations on Issue's API

Scenario Outline: CRUD Operation on Issue API
	Given User calls NewIssue API  with  payload "<title>" "<description>" "<assigneeId>" "<labels>" "<type>"
	And 	API call success "state" equals "opened"
	And 	API call success "author.username" equals "archutce"
	And 	API call success "author.name" equals "Archana Velu"
	And 	API call success "author.state" equals "active"
	And 	API call success "author.locked" equals "false"
	And 	API call success "severity" equals "UNKNOWN"
	And 	User calls getIssue API to retrive details "afterCreateIssue"
	And 	User calls EditIssue API to update the details
	And 	User calls getIssue API to retrive details "afterUpdateIssue"
	And 	User calls DeleteIssue API to delete the issue
	And 	User calls getIssue API to retrive details "afterDeleteIssue"
	
	
Examples:
	|title 							|description 						 |assigneeId |labels |type			|
	|New Issue title 1  |New Issue1 description  |123456 		 |bug		 |issue  		|
	|New Issue title 2	|New issue2 description  |345678		 |smoke	 |incident	|
	

	

