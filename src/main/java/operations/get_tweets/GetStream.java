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
        ParseList parseList = new ParseList();
        ArrayList<String> dirtyWords = parseList.filteredWords();
        DownloadPics downloadPics = new DownloadPics();
        GoogleDrive googleDrive = new GoogleDrive();
        TweetsToDB tweetsToDB = new TweetsToDB();
        class StatusListener implements twitter4j.StatusListener {
            private int tweetNumber = 1;
            @Override
            public void onStatus(Status status) {
                if (parseList.stringContainsItemFromList(status.getText(), dirtyWords)) {
                    System.out.println("SPAM");
                } else {
                    if (uploadPictures) {
                        for (MediaEntity mediaEntity : status.getMediaEntities()) {
                            if (mediaEntity.getType().equals("photo")) {
                                downloadPics.download(mediaEntity.getMediaURL());
                                googleDrive.executeOperations();
                            }
                        }
                    }
                    System.out.println(tweetNumber + " @" + status.getUser().getScreenName() + " - " + status.getText());
                    if (insertIntoDB) {
                        tweetsToDB.storeTweets(status);
                    }
                }
                tweetNumber++;
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        StatusListener statusListener = new StatusListener();
        twitterStream.addListener(statusListener);
        long startTime = System.currentTimeMillis();

        if(useFiltering) {
            FilterQuery tweetFilterQuery = new FilterQuery();
            tweetFilterQuery.track(filterWord);
            tweetFilterQuery.language(language);
            twitterStream.filter(tweetFilterQuery);
            while(System.currentTimeMillis() < startTime + streamLength * 60000){}
            twitterStream.removeListener(statusListener);
            twitterStream.shutdown();
        } else {
            twitterStream.sample(language);
            while(System.currentTimeMillis() < startTime + streamLength * 60000){}
            twitterStream.removeListener(statusListener);
            twitterStream.shutdown();
        }
    }
}
