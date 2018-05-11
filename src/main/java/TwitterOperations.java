import credentials.*;
import operations.get_tweets.ParseTimeLine;
import operations.get_tweets.SearchByExpression;
import operations.get_tweets.Stream;
import operations.posting.PostTweet;
import operations.posting.TimedTweet;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Calendar;
import java.util.Date;

public class TwitterOperations {
    public static void main(String[] args) {
        ParseTimeLine parseTimeline = new ParseTimeLine();
        SearchByExpression searchByExpression = new SearchByExpression();
        Stream stream = new Stream();
        ConfigMaker configMaker = new ConfigMaker();
        ConfigurationBuilder configurationBuilder = configMaker.buildConfig();
        Configuration config = configurationBuilder.build();
        TwitterFactory twitterFactory = new TwitterFactory(config);
        TwitterStream twitterStream = new TwitterStreamFactory(config).getInstance();
        TwitterOperations twitterOperations = new TwitterOperations();
        PostTweet postTweet = new PostTweet(twitterFactory);

        int hour = Integer.parseInt(args[0]);
        int minute = Integer.parseInt(args[1]);
        int second = Integer.parseInt(args[2]);
        Date date = twitterOperations.setTime(hour, minute, second);
        String twitterUser = args[3];
        int pageNumber = Integer.parseInt(args[4]);
        int count1 = Integer.parseInt(args[5]);
        String expression = args[6];
        int count2 = Integer.parseInt(args[7]);
        boolean putResultsInDB1 = Boolean.parseBoolean(args[8]);
        String filterWord = args[9];
        String filterLanguage = args[10];
        boolean putResultsInDB2 = Boolean.parseBoolean(args[11]);
        boolean useFilter = Boolean.parseBoolean(args[12]);
        boolean uploadPictures = Boolean.parseBoolean(args[13]);

        // Post tweet
        postTweet.post("asd");
        // Post timed tweet
        // new TimedTweet(date, twitterFactory);
        // Get timeline of a user
        // parseTimeline.getTimeLine(twitterUser, twitterFactory, pageNumber, count1);
        // Search for an expression
        searchByExpression.search(expression, twitterFactory, count2, putResultsInDB1);
        // Get a stream of tweets
        stream.stream(twitterStream, filterWord, filterLanguage, putResultsInDB2, useFilter, uploadPictures);
    }

    public Date setTime(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }
}