package operations.get_tweets;

import twitter4j.*;

import java.util.List;

public class ParseTimeLine {

    public void getTimeLine(String userName, TwitterFactory tf, int pageNumber, int count) {
        Twitter twitter = tf.getInstance();
        try {
            List<Status> statuses;
            Paging page = new Paging (pageNumber, count);
            User user = twitter.showUser(userName);
            statuses = twitter.getUserTimeline(user.getId(), page);
            int tweetNumber = 1;
            for (Status status : statuses) {
                System.out.println(tweetNumber + " @" + status.getUser().getScreenName() + " - " + status.getText());
                tweetNumber++;
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
