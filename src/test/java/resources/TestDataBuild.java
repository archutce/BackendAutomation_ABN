package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.EditIssueRequest;
import pojo.NewIssueRequest;

public class TestDataBuild {
	
	String sheetname = "IssueAPIData";
	String testCaseName = "EditIssueAPI";

	
	//using POJO
	public NewIssueRequest newIssuePayload(String title, String description, String assigneeId, String labels, String type) {
		NewIssueRequest newIssue=new NewIssueRequest();
		newIssue.setTitle(title);
		newIssue.setDescription(description);
		newIssue.setAssignee_id(assigneeId);
		newIssue.setLabels(labels);
		newIssue.setIssue_type(type);
		return newIssue;
	}
	
	//using hashmap along with data driven from excel
	public HashMap<String, Object> editIssuePayload() throws IOException {
		HashMap<String,Object> editIssueMap= new HashMap<>();
		
		DataDriven d=new DataDriven();
		ArrayList<Object> dataValue=d.getDataFromExcel(sheetname, testCaseName);
		
		editIssueMap.put("issue_type", dataValue.get(1));
		editIssueMap.put("title", dataValue.get(2));
		editIssueMap.put("remove_labels", dataValue.get(3));
		editIssueMap.put("confidential", dataValue.get(4));
		editIssueMap.put("discussion_locked", dataValue.get(5));
		return editIssueMap;
			
	} 
	
	public String mandatoryRequest() {		
		return "";
		
	}
	

}
