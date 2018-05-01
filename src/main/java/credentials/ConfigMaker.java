package credentials;

import twitter4j.conf.ConfigurationBuilder;

public class ConfigMaker {

    public ConfigurationBuilder buildConfig() {
        ParseCredentials parseCredentials = new ParseCredentials();
        Credentials credentials = parseCredentials.read();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(credentials.getConsumerKey())
                .setOAuthConsumerSecret(credentials.getConsumerSecret())
                .setOAuthAccessToken(credentials.getAccessToken())
                .setOAuthAccessTokenSecret(credentials.getAccessTokenSecret())
                .setTweetModeExtended(true);
        return cb;
    }
}
