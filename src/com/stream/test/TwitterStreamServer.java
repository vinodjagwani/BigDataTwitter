package com.stream.test;

import com.twitter.streamData.TwitterStreamConsumer;

public class TwitterStreamServer {
    public static void main(String[] args){

        final TwitterStreamConsumer streamConsumer = new TwitterStreamConsumer(); // final because we will later pull the latest Tweet
       streamConsumer.start();
    }
}