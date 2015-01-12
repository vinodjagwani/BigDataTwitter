package com.twitter.streamData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.mongodb.util.JSON;


public class TwitterStreamConsumer extends Thread {

    private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/sample.json";

    public void run(){
        try{
            System.out.println("Starting Twitter public stream consumer thread.");

            // Enter your consumer key and secret below
            OAuthService service = new ServiceBuilder().provider(TwitterApi.class).apiKey("fbrlVNGvUrosXLzue4PFTg").apiSecret("lrNjzAHu3TlHYb2LEUKEGwG1hXzY7UsK0yFoKrwEW5g").build();

            // Set your access token
            Token accessToken = new Token("92247221-QJXtF3iPuZMyV9bkSmMqD4eKDRgmeq817qpTyxISb", "Gl0873fXOe9n0HOw94Tae7XizfJuHHoujVuXXGbcesx8Q");

            // Let's generate the request
            System.out.println("Connecting to Twitter Public Stream");
            OAuthRequest request = new OAuthRequest(Verb.GET, STREAM_URI);
            request.addHeader("version", "HTTP/1.1");
            request.addHeader("host", "stream.twitter.com");
            request.setConnectionKeepAlive(true);
            request.addHeader("user-agent", "Twitter Stream Reader");
            request.addBodyParameter("track","USA"); // Set keywords yo'd like to track here
            service.signRequest(accessToken, request);
            Response response = request.send();
            
            // Create a reader to read Twitter's stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (Exception ioe){
            ioe.printStackTrace();
        }     
        }

    }