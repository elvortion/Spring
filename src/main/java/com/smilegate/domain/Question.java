package com.smilegate.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id @GeneratedValue
	private long id;
	
	//@Column
	@ManyToOne
	User writer;
	
//	@Column(length = 15, nullable = false)
//	private String writer;
	
	@Column(length = 50, nullable = false)
	private String title;
	
	@Column(length = 500, nullable = false)
	private String contents;
	
	@Column(length = 100, nullable = false)
	private Date time;
	//private String time;
	//private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm");
	
	

	public Question() {
		this.time = new Date();
	}
	
	public Question(User user, Question question) {
		this.time = new Date();
		this.writer = question.writer;
		this.title = question.title;
		this.contents = question.contents;
	}
	
	public Question(Question question) {
		this.time = new Date();
		this.writer = question.writer;
		this.title = question.title;
		this.contents = question.contents;
	}
	
	public String toString() {
		return "Writer : " + this.writer.toString() + "\n" + "Time : " + this.time.toString() + " | Title : " + this.title;
	}
	
	public boolean isMatchWriter(User user) {
		return this.writer.getId() == user.getId();
	}
	
	public boolean isMatchId(long id) {
		return this.writer.getId() == id;
	}

	public void update(Question newQuestion) {
		if (isMatchId(newQuestion.writer.getId())) {
			this.title = newQuestion.title;
			this.contents = newQuestion.contents;
		}	
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
