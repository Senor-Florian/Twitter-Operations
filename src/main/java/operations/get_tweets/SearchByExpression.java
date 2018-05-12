package operations.get_tweets;

import operations.misc.TweetsToDB;
import twitter4j.*;

public class SearchByExpression {

    public void search(String inputQuery, TwitterFactory tf, int count, boolean insertIntoDB) {
        Twitter twitter = tf.getInstance();
        Query query = new Query(inputQuery);
        query.setCount(count);
        QueryResult queryResult = null;
        TweetsToDB tweetsToDB = new TweetsToDB();
        try {
            queryResult = twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        int tweetNumber = 1;
        for (Status status : queryResult.getTweets()) {
            System.out.println(tweetNumber + " @" + status.getUser().getScreenName() + " - " + status.getText());
            if(insertIntoDB) {
                tweetsToDB.storeTweets(status);
            }
            tweetNumber++;
        }

    }
}
