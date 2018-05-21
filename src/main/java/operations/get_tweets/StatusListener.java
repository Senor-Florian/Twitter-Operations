package operations.get_tweets;

import operations.misc.DownloadPics;
import operations.misc.GoogleDrive;
import operations.misc.ParseList;
import operations.misc.TweetsToDB;
import twitter4j.MediaEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;

import java.util.ArrayList;

class StatusListener implements twitter4j.StatusListener {
    private int tweetNumber = 1;
    private boolean uploadPictures;
    private boolean insertIntoDB;
    private ParseList parseList = new ParseList();
    private ArrayList<String> dirtyWords = parseList.filteredWords();
    private DownloadPics downloadPics = new DownloadPics();
    private GoogleDrive googleDrive = new GoogleDrive();
    private TweetsToDB tweetsToDB = new TweetsToDB();

    public StatusListener(boolean uploadPictures, boolean insertIntoDB) {
        this.uploadPictures = uploadPictures;
        this.insertIntoDB = insertIntoDB;
    }

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
            System.out.println("((" + tweetNumber + ")) " + status.getCreatedAt() + " @" + status.getUser().getScreenName() + " - " + status.getText());
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
}