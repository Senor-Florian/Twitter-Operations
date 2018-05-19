package operations.posting;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PostTweet {

    private TwitterFactory twitterFactory;
    private Twitter twitter;

    public PostTweet(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
    }

    public void post(String tweetBody) {
        try {
            twitter.updateStatus(tweetBody);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
