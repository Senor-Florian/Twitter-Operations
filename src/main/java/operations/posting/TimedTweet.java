package operations.posting;

import operations.posting.PostTweet;
import twitter4j.TwitterFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimedTweet {

    Timer timer;

    public TimedTweet(Date time, TwitterFactory tf) {
        timer = new Timer();
        timer.schedule(new SendTimedTweet(tf), time);
    }
    class SendTimedTweet extends TimerTask {
        TwitterFactory tf;
        public SendTimedTweet(TwitterFactory tf) {
            this.tf = tf;
        }
        public void run() {
            PostTweet postTweet = new PostTweet();
            postTweet.createTweet("tweet", tf);
            System.out.println("Tweet sent.");
            timer.cancel();
        }
    }
}
