package operations.get_tweets;

import operations.misc.TweetsToDB;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class SearchByExpression {

    public void search(String inputQuery, TwitterFactory tf, int count, boolean insertIntoDB) {
        Twitter twitter = tf.getInstance();
        Query query = new Query(inputQuery);
        QueryResult queryResult;
        TweetsToDB tweetsToDB = new TweetsToDB();
        List<Status> statuses = new ArrayList<>();
        while (statuses.size() < count) {
            if (count - statuses.size() > 100) {
                query.setCount(100);
            } else {
                query.setCount(count - statuses.size());
            }
            try {
                queryResult = twitter.search(query);
                statuses.addAll(queryResult.getTweets());
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

        int tweetNumber = 1;
        for (Status status : statuses) {
            System.out.println("((" + tweetNumber + ")) " + " @" + status.getUser().getScreenName() + " - " + status.getText());
            if (insertIntoDB) {
                tweetsToDB.storeTweets(status);
            }
            tweetNumber++;
        }
    }
}
