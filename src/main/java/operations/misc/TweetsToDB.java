package operations.misc;

import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TweetsToDB {

    public void storeTweets(Status status) {
        java.sql.Connection connection;
        String db = "jdbc:mysql://localhost:3306/twitter";
        String user = "root";
        String password = "toor";
        HashtagEntity[] hashtagEntities = status.getHashtagEntities();
        StringBuilder hashtags = new StringBuilder();
        for(HashtagEntity hashtagEntity : hashtagEntities) {
            hashtags.append("#" + hashtagEntity.getText());
        }
                try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db, user, password);
            Statement statement = connection.createStatement();
            String command = "insert into tweets (posted_at, text, name, screen_name, location, url, description, hashtags) values " +
                    "('" + status.getCreatedAt().toString() + "', '" +
                        status.getText().replace("'", "\\'") + "', '" +
                        status.getUser().getName().replace("'", "\\'") + "', '" +
                        status.getUser().getScreenName().replace("'", "\\'") + "', '" +
                        status.getUser().getLocation().replace("'", "\\'") + "', '" +
                        status.getUser().getURL() + "', '" +
                        status.getUser().getDescription().replace("'", "\\'") + "', '" +
                        hashtags.toString() +
                     "');";
            statement.executeUpdate(command);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
