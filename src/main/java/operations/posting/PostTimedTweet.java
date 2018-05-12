package operations.posting;

import twitter4j.TwitterFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PostTimedTweet {

    private Timer timer;

    public PostTimedTweet(int hour, int minute, int second, TwitterFactory tf, String tweetBody) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        timer = new Timer();
        timer.schedule(new SendTimedTweet(tf, tweetBody), calendar.getTime());
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
