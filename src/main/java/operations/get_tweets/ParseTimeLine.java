package operations.get_tweets;

import operations.misc.TweetsToDB;
import twitter4j.*;

import java.util.List;

public class ParseTimeLine {

    public void getTimeLine(String userName, TwitterFactory tf, int pageNumber, int count, boolean insertIntoDB) {
        Twitter twitter = tf.getInstance();
        TweetsToDB tweetsToDB = new TweetsToDB();
        try {
            List<Status> statuses;
            Paging page = new Paging (pageNumber, count);
            User user = twitter.showUser(userName);
            statuses = twitter.getUserTimeline(user.getId(), page);
            int tweetNumber = 1;
            for (Status status : statuses) {
                System.out.println(tweetNumber + " " + status.getCreatedAt() + " @" + status.getUser().getScreenName() + " - " + status.getText());
                if(insertIntoDB) {
                    tweetsToDB.storeTweets(status);
                }
                tweetNumber++;
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
