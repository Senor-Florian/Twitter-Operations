package webapp.models;

public class TimeLine {

    private String handle;
    private int page;
    private int count;
    private boolean putInDB;
    private boolean getAllTweets;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPutInDB() {
        return putInDB;
    }

    public void setPutInDB(boolean putInDB) {
        this.putInDB = putInDB;
    }

    public boolean isGetAllTweets() {
        return getAllTweets;
    }

    public void setGetAllTweets(boolean getAllTweets) {
        this.getAllTweets = getAllTweets;
    }
}
