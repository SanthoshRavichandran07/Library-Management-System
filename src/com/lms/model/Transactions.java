package com.lms.model;

public class Transactions {
	private int id;
	private int bookId;
	private int memberId;
	private String issueDate;
	private String returnDate;
	private int status;
	
	public Transactions(){}
	public Transactions(int id) {
		this.id=id;
	}
	public Transactions(int bookId, int memberId){
		this.bookId = bookId;
		this.memberId = memberId;
	}
//	getters & setters
	
	public void setId(int id) {
		this.id =id;
	}
	public int getId() {
		return id;
	}
	
	public void setBookId(int bookId) {
		this.bookId =bookId;
		
	}
	public int getBookId() {
		return bookId;
	}
	public void setMemberId(int memberId) {
		this.memberId =memberId;
		
	}
	public int getMemberId() {
		return memberId;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
		
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
}
