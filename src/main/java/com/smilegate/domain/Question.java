package com.smilegate.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Question {
	private String writer;
	private String title;
	private String contents;
	private Date time;
	//private String time;
	//private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm");
	
	public Question() {
		this.time = new Date();
	}
	
//	public Question(String writer, String title, String contents) {
//		this.writer = writer;
//		this.title = title;
//		this.contents = contents;
////		this.lTime = System.currentTimeMillis();
////		this.time = format.format(lTime).toString();
//	}


	public String getWriter() {
		return writer;
	}

	public void setWriter(String author) {
		this.writer = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
