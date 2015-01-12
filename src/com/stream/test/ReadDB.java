package com.stream.test;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.twitter.beans.RootElements;

public class ReadDB {

	public static void main(String[] args) {

		
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("test");
			final DBCollection coll = db.getCollection("twstream");
			DBCursor cursor = coll.find();
			Gson gson=new Gson();
			int i =1;
			while(cursor.hasNext()) {
			RootElements pojo = gson.fromJson(cursor.next().toString(), RootElements.class);
			System.out.println(i+". "+pojo.getUser().getId());
			System.out.println(pojo.getUser().getScreen_name());
			System.out.println("Indegree : "+pojo.getUser().getFollowers_count());
			System.out.println("Mentions : "+pojo.getEntities().getUser_mentions().size());
			if(null!=pojo.getRetweeted_status()){
				if(Integer.parseInt(pojo.getRetweeted_status().getRetweet_count()) > 0){
				System.out.println("Retweet : "+pojo.getRetweeted_status().getRetweet_count());
				}
			}
			
//			System.out.println(pojo.getUser().getFollowers_count());
//			if(null != pojo.getRetweeted_status()){
//				System.out.println("count of retweeter "+pojo.getRetweeted_status().getRetweet_count());
//				System.out.println("screen name of retweeter "+pojo.getRetweeted_status().getUser().getScreen_name());
//
//			}
//			if(pojo.getEntities().getUser_mentions().size() > 0){
//				for(int i =0 ;i<pojo.getEntities().getUser_mentions().size();i++){
//					System.out.println("User_Mentions : "+pojo.getEntities().getUser_mentions().get(i).getId());
//					System.out.println("User_Mentions : "+pojo.getEntities().getUser_mentions().get(i).getScreen_name());
//
//				}
//			}
//			System.out.println(pojo.getText());
			System.out.println("______________________________________");
			i++;
			}
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
	}

}
