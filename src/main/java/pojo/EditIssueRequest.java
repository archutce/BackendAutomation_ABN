package pojo;

import java.util.List;

public class EditIssueRequest {
	
	private String issue_type;
	private List<Integer> assignee_ids;
	private String title;
	private String remove_labels;
	private boolean confidential;
	private boolean discussion_locked;
	private String due_date;
	private String state_event;
	
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public List<Integer> getAssignee_ids() {
		return assignee_ids;
	}
	public void setAssignee_ids(List<Integer> assignee_ids) {
		this.assignee_ids = assignee_ids;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemove_labels() {
		return remove_labels;
	}
	public void setRemove_labels(String remove_labels) {
		this.remove_labels = remove_labels;
	}
	public boolean isConfidential() {
		return confidential;
	}
	public void setConfidential(boolean confidential) {
		this.confidential = confidential;
	}
	public boolean isDiscussion_locked() {
		return discussion_locked;
	}
	public void setDiscussion_locked(boolean discussion_locked) {
		this.discussion_locked = discussion_locked;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getState_event() {
		return state_event;
	}
	public void setState_event(String state_event) {
		this.state_event = state_event;
	}


}
