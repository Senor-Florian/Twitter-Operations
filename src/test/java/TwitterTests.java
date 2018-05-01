import credentials.ConfigMaker;
import operations.misc.ParseList;
import operations.posting.PostTweet;
import org.junit.Test;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import static org.junit.Assert.assertNotEquals;

public class TwitterTests {

    PostTweet postTweet = new PostTweet();
    ConfigMaker configMaker = new ConfigMaker();
    ConfigurationBuilder configurationBuilder = configMaker.buildConfig();
    Configuration config = configurationBuilder.build();
    TwitterFactory twitterFactory = new TwitterFactory(config);
    ParseList parseList = new ParseList();

    @Test
    public void tweetReadSuccessTest() {
        assertNotEquals(null, postTweet.createTweet("tweet", twitterFactory));
    }

    @Test
    public void listReadSuccessTest() {
        assertNotEquals(null, parseList.filteredWords());
    }
}
