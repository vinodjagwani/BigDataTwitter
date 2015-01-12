package com.twitter.streamData;

import twitter4j.StallWarning;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.StatusListener;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;


public class TwitterDump {
	
	
    public static void main(String[] args) throws TwitterException, IOException {
        
    	
    	Mongo m = new Mongo("localhost", 27017);
        DB db = m.getDB("test");
        int tweetCount = args[0].length() > 0 ? Integer.parseInt(args[0]) : 100;

          final DBCollection coll = db.getCollection("twstream");
          ConfigurationBuilder cb = new ConfigurationBuilder();
		  cb.setDebugEnabled(true);
		  cb.setOAuthConsumerKey("fbrlVNGvUrosXLzue4PFTg");
		  cb.setOAuthConsumerSecret("lrNjzAHu3TlHYb2LEUKEGwG1hXzY7UsK0yFoKrwEW5g");
		  cb.setOAuthAccessToken("92247221-QJXtF3iPuZMyV9bkSmMqD4eKDRgmeq817qpTyxISb");
		  cb.setOAuthAccessTokenSecret("Gl0873fXOe9n0HOw94Tae7XizfJuHHoujVuXXGbcesx8Q");
		  cb.setJSONStoreEnabled(true);
		  
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
           
        	public void onStatus(Status status) {
                String tweet = DataObjectFactory.getRawJSON(status);
                System.out.println(tweet);
                DBObject doc = (DBObject)JSON.parse(tweet);
                coll.insert(doc);
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onScrubGeo(long l, long l1) {
            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }

			public void onStallWarning(StallWarning arg0) {
				
			}
        };
        
        
        twitterStream.addListener(listener);
        twitterStream.retweet();
        
        
        while (coll.count() < tweetCount) {

        }
        twitterStream.shutdown();

    }
    
}