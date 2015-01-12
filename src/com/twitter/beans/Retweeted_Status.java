package com.twitter.beans;

public class Retweeted_Status {

	private String text;
	private Entities entities;
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Entities getEntities() {
		return entities;
	}
	public void setEntities(Entities entities) {
		this.entities = entities;
	}
	public String getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(String retweet_count) {
		this.retweet_count = retweet_count;
	}

	private String retweet_count;
}
