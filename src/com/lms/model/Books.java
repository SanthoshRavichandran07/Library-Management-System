package com.lms.model;

public class Books {
	private int id;
	private String title;
	private String author;
	private int year;
	private String publisherName;
	private String genre;
	private String availability;
	
	public Books(){}
	
	public Books( String title, String author, int year, String publisherName, String genre, String availability){
//		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.publisherName = publisherName;
		this.genre = genre;
		this.availability = availability;
	}
	
	// setters and getters
	
	// Book Id

	public int getId() {
		return id;
	}
	// Book title
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	// Book published year
	public void setYear(int year) {
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	// Book's Author
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}
	// Book publisher name
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	// Book Genre Type
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getGenre() {
		return genre;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getAvailability() {
		return availability;
	}
}
