package operations.posting;

import twitter4j.TwitterFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimedTweet {

    private Timer timer;

    public TimedTweet(Date time, TwitterFactory tf) {
        timer = new Timer();
        timer.schedule(new SendTimedTweet(tf), time);
    }
    class SendTimedTweet extends TimerTask {
        TwitterFactory tf;
        SendTimedTweet(TwitterFactory tf) {
            this.tf = tf;
        }
        public void run() {
            PostTweet postTweet = new PostTweet("tweet", tf);
            postTweet.post(postTweet.createTweet());
            System.out.println("Tweet sent.");
            timer.cancel();
        }
    }
}
