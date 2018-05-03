import credentials.ConfigMaker;
import credentials.Credentials;
import credentials.ParseCredentials;
import operations.misc.ParseList;
import operations.posting.PostTweet;
import org.junit.Test;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

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
    public void tweetExistsTest() {
        assertTrue("Tweet file doesn't exist.", new File(ClassLoader.getSystemClassLoader().getResource("tweet").getPath()).exists());
    }

    @Test
    public void listExistsTest() {
        assertTrue("Spam list doesn't exist.", new File("sex.txt").exists());
    }

    @Test
    public void credentialsExistsTest() {
        assertTrue("Credentials file doesn't exist.", new File(ClassLoader.getSystemClassLoader().getResource("credentials.json").getPath()).exists());
    }

    @Test
    public void tweetReadSuccessTest() {
        assertNotNull(postTweet.createTweet());
    }

    @Test
    public void listReadSuccessTest() {
        assertNotNull(parseList.filteredWords());
    }

    @Test
    public void credentialsReadSuccessTest() {
        assertNotNull(parseCredentials.read());
    }

    @Test
    public void consumerKeyTest() {
        assertTrue(pattern1.matcher(credentials.getConsumerKey()).matches());
    }

    @Test
    public void consumerSecretTest() {
        assertTrue(pattern1.matcher(credentials.getConsumerSecret()).matches());
    }

    @Test
    public void accessTokenTest() {
        assertTrue(pattern2.matcher(credentials.getAccessToken()).matches());
    }

    @Test
    public void accessTokenSecretTest() {
        assertTrue(pattern1.matcher(credentials.getAccessTokenSecret()).matches());
    }
}
