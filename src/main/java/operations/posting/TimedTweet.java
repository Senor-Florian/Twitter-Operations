package operations.posting;

import twitter4j.TwitterFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimedTweet {

    private Timer timer;

    public TimedTweet(Date time, TwitterFactory tf, String tweetBody) {
        timer = new Timer();
        timer.schedule(new SendTimedTweet(tf, tweetBody), time);
    }
    class SendTimedTweet extends TimerTask {
        TwitterFactory tf;
        String tweetBody;
        SendTimedTweet(TwitterFactory tf, String tweetBody) {
            this.tf = tf;
            this.tweetBody = tweetBody;
        }
        public void run() {
            PostTweet postTweet = new PostTweet(tf);
            postTweet.post(tweetBody);
            System.out.println("Tweet sent.");
            timer.cancel();
        }
    }
}
