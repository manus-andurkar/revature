package com.revature.model;

public class Request {
	private int requestID;
	private String title;
	private String body;
	private boolean resolved;
	private int empID;
	
	public Request(int requestID, String title, String body, boolean resolved, int empID) {
		super();
		this.requestID = requestID;
		this.title = title;
		this.body = body;
		this.resolved = resolved;
		this.empID = empID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", title=" + title + ", body=" + body
				+ ", resolved=" + resolved + "]";
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}
}
