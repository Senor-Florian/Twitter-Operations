import credentials.ConfigMaker;
import credentials.Credentials;
import credentials.ParseCredentials;
import operations.misc.ParseList;
import operations.posting.PostTweet;
import org.junit.Test;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TwitterTests {

    private ConfigMaker configMaker = new ConfigMaker();
    private ConfigurationBuilder configurationBuilder = configMaker.buildConfig();
    private Configuration config = configurationBuilder.build();
    private TwitterFactory twitterFactory = new TwitterFactory(config);
    private ParseList parseList = new ParseList();
    private ParseCredentials parseCredentials = new ParseCredentials();
    private Credentials credentials = parseCredentials.read();
    private PostTweet postTweet = new PostTweet("tweet", twitterFactory);
    private String regex1 = "^[a-zA-Z0-9]*$";
    private String regex2 = "^[a-zA-Z0-9-]*$";
    private Pattern pattern1 = Pattern.compile(regex1);
    private Pattern pattern2 = Pattern.compile(regex2);

    @Test
    public void tweetReadSuccessTest() {
        assertNotEquals(null, postTweet.createTweet());
    }

    @Test
    public void listReadSuccessTest() {
        assertNotEquals(null, parseList.filteredWords());
    }

    @Test
    public void credentialsReadSuccessTest() {
        assertNotEquals(null, parseCredentials.read());
    }

    @Test
    public void consumerKeyTest() {
        assertEquals(true, pattern1.matcher(credentials.getConsumerKey()).matches());
    }

    @Test
    public void consumerSecretTest() {
        assertEquals(true, pattern1.matcher(credentials.getConsumerSecret()).matches());
    }

    @Test
    public void accessTokenTest() {
        assertEquals(true, pattern2.matcher(credentials.getAccessToken()).matches());
    }

    @Test
    public void accessTokenSecretTest() {
        assertEquals(true, pattern1.matcher(credentials.getAccessTokenSecret()).matches());
    }
}
