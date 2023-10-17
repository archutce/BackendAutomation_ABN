package pojo;

import java.util.List;

public class NewIssueResponse {

	private String id;
	private String iid;
	private String project_id;
	private String title;
	private String description;
	private String state;
	private String created_at;
	private String updated_at;
	private String closed_at;
	private String closed_by;
	private List<String> labels;
	private String milestone;
	private List<String> assignees;
	private Author author;
	private String type;
	private String assignee;
	private String user_notes_count;
	private String merge_requests_count;
	private String upvotes;
	private String downvotes;
	private String due_date;
	private boolean confidential;
	private boolean discussion_locked;
	private String issue_type;
	private String web_url;
	private Timestats time_stats;
	private TaskCompletionStatus task_completion_status;
	private String blocking_issues_count;
	private boolean has_tasks;
	private String task_status;
	private Links _links;
	private References references;
	private String severity;
	private boolean subscribed;
	private String moved_to_id;
	private String service_desk_reply_to;
	
	public boolean isHas_tasks() {
		return has_tasks;
	}
	public void setHas_tasks(boolean has_tasks) {
		this.has_tasks = has_tasks;
	}
	public boolean isSubscribed() {
		return subscribed;
	}
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getClosed_at() {
		return closed_at;
	}
	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}
	public String getClosed_by() {
		return closed_by;
	}
	public void setClosed_by(String closed_by) {
		this.closed_by = closed_by;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public String getMilestone() {
		return milestone;
	}
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}
	public List<String> getAssignees() {
		return assignees;
	}
	public void setAssignees(List<String> assignees) {
		this.assignees = assignees;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getUser_notes_count() {
		return user_notes_count;
	}
	public boolean isConfidential() {
		return confidential;
	}
	public boolean isDiscussion_locked() {
		return discussion_locked;
	}


	public void setUser_notes_count(String user_notes_count) {
		this.user_notes_count = user_notes_count;
	}
	public String getMerge_requests_count() {
		return merge_requests_count;
	}
	public void setMerge_requests_count(String merge_requests_count) {
		this.merge_requests_count = merge_requests_count;
	}
	public String getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(String upvotes) {
		this.upvotes = upvotes;
	}
	public String getDownvotes() {
		return downvotes;
	}
	public void setDownvotes(String downvotes) {
		this.downvotes = downvotes;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public void setConfidential(boolean confidential) {
		this.confidential = confidential;
	}
	public void setDiscussion_locked(boolean discussion_locked) {
		this.discussion_locked = discussion_locked;
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public Timestats getTime_stats() {
		return time_stats;
	}
	public void setTime_stats(Timestats time_stats) {
		this.time_stats = time_stats;
	}
	public TaskCompletionStatus getTask_completion_status() {
		return task_completion_status;
	}
	public void setTask_completion_status(TaskCompletionStatus task_completion_status) {
		this.task_completion_status = task_completion_status;
	}
	public String getBlocking_issues_count() {
		return blocking_issues_count;
	}
	public void setBlocking_issues_count(String blocking_issues_count) {
		this.blocking_issues_count = blocking_issues_count;
	}

	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	public Links get_links() {
		return _links;
	}
	public void set_links(Links _links) {
		this._links = _links;
	}
	public References getReferences() {
		return references;
	}
	public void setReferences(References references) {
		this.references = references;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getMoved_to_id() {
		return moved_to_id;
	}
	public void setMoved_to_id(String moved_to_id) {
		this.moved_to_id = moved_to_id;
	}
	public String getService_desk_reply_to() {
		return service_desk_reply_to;
	}
	public void setService_desk_reply_to(String service_desk_reply_to) {
		this.service_desk_reply_to = service_desk_reply_to;
	}
	
	
	
}
