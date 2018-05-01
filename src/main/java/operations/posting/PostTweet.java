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

    private String tweetPath;
    private TwitterFactory twitterFactory;
    private Twitter twitter;

    public PostTweet(String tweetPath, TwitterFactory twitterFactory) {
        this.tweetPath = tweetPath;
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
    }

    public StringBuilder createTweet(){
        StringBuilder tweet = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(tweetPath).getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                tweet.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tweet;
    }

    public void post(StringBuilder tweet) {
        try {
            twitter.updateStatus(tweet.toString());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
