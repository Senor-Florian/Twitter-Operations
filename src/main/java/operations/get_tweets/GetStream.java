package operations.get_tweets;

import twitter4j.*;

public class GetStream {

    public void stream(TwitterStream twitterStream, String filterWord, String language,
                       boolean insertIntoDB, boolean useFiltering, boolean uploadPictures,
                       int streamLength) {

        StatusListener statusListener = new StatusListener(uploadPictures, insertIntoDB);
        twitterStream.addListener(statusListener);
        long startTime = System.currentTimeMillis();

        if(useFiltering) {
            FilterQuery tweetFilterQuery = new FilterQuery();
            tweetFilterQuery.track(filterWord);
            tweetFilterQuery.language(language);
            twitterStream.filter(tweetFilterQuery);
            while(true){
                if (!(System.currentTimeMillis() < startTime + streamLength * 1000)) break;
            }
            twitterStream.removeListener(statusListener);
            twitterStream.shutdown();
        } else {
            twitterStream.sample(language);
            while(true){
                if (!(System.currentTimeMillis() < startTime + streamLength * 1000)) break;
            }
            twitterStream.removeListener(statusListener);
            twitterStream.shutdown();
        }
    }
}
