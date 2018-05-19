package webapp.models;

public class Expression {

    private String expression;
    private int count;
    private boolean putInDB;
    private boolean getAllTweets;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
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
