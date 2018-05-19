package operations.get_tweets;

import operations.misc.DownloadPics;
import operations.misc.GoogleDrive;
import operations.misc.ParseList;
import operations.misc.TweetsToDB;
import twitter4j.*;
import java.io.File;
import java.net.URL;
import org.apache.commons.io.FileUtils;

import java.util.ArrayList;

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
                if (!(System.currentTimeMillis() < startTime + streamLength * 20000)) break;
            }
            /*
            while(true) {
                if (!(statusListener.getTweetNumber() < 667)) break;
            }*/
            twitterStream.removeListener(statusListener);
            twitterStream.shutdown();
        } else {
            twitterStream.sample(language);
            while(true){
                if (!(System.currentTimeMillis() < startTime + streamLength * 20000)) break;
            }
            /*
            while(true) {
                if (!(statusListener.getTweetNumber() < 667)) break;
            }*/
            twitterStream.removeListener(statusListener);
            twitterStream.shutdown();
        }
    }
}
