package com.twitter.beans;

import com.twitter.beans.Entities;
import com.twitter.beans.Retweeted_Status;
import com.twitter.beans.User;

public class RootElements {

	private String text;
	private User user;
	private Entities entities;
	private Retweeted_Status retweeted_status;
	
	
	public Retweeted_Status getRetweeted_status() {
		return retweeted_status;
	}

	public void setRetweeted_status(Retweeted_Status retweeted_status) {
		this.retweeted_status = retweeted_status;
	}

	public Entities getEntities() {
		return entities;
	}

	public void setEntities(Entities entities) {
		this.entities = entities;
	}

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
	
	
	
	
}
