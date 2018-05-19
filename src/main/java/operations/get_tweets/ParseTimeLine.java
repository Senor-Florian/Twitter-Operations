package operations.get_tweets;

import operations.misc.TweetsToDB;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class ParseTimeLine {

    public void getTimeLine(String userName, TwitterFactory tf, int pageNumber, int count,
                            boolean insertIntoDB, boolean getAllTweets) {
        Twitter twitter = tf.getInstance();
        TweetsToDB tweetsToDB = new TweetsToDB();
        try {
            List<Status> statuses = new ArrayList<>();
            int timeLineLength;
            User user = twitter.showUser(userName);
            if(getAllTweets) {
                int currentPage = 1;
                int tweetPerPage = 50;
                Paging page = new Paging (currentPage, tweetPerPage);
                while(true) {
                    timeLineLength = statuses.size();
                    statuses.addAll(twitter.getUserTimeline(user.getId(), page));
                    page.setPage(++currentPage);
                    if(statuses.size() == timeLineLength) {
                        break;
                    }
                }
            } else {
                Paging page = new Paging (pageNumber, count);
                statuses = twitter.getUserTimeline(user.getId(), page);
            }

            int tweetNumber = 1;
            for (Status status : statuses) {
                System.out.println("((" + tweetNumber + ")) " + status.getCreatedAt() + " @" + status.getUser().getScreenName() + " - " + status.getText());
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
