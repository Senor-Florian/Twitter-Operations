package webapp.controllers;

import credentials.ConfigMaker;
import operations.get_tweets.ParseTimeLine;
import operations.posting.PostTimedTweet;
import operations.posting.PostTweet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.exceptions.TemplateProcessingException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import webapp.models.TimeLine;
import webapp.models.TimedTweet;
import webapp.models.Tweet;

@Controller
@RequestMapping(path="/twitter")
public class MainController {
    private ConfigMaker configMaker;
    private ConfigurationBuilder configurationBuilder;
    private Configuration config;
    private TwitterFactory twitterFactory;
    private PostTweet postTweet;
    private ParseTimeLine parseTimeline;


    public MainController() {
        this.configMaker = new ConfigMaker();
        this.configurationBuilder = configMaker.buildConfig();
        this.config = configurationBuilder.build();
        this.twitterFactory = new TwitterFactory(config);
        this.postTweet = new PostTweet(twitterFactory);
        this.parseTimeline = new ParseTimeLine();
    }

    @GetMapping(path = "/")
    public String main() {
        return "main";
    }

    @GetMapping(path = "/tweet")
    public String getTweetForm(Model model) {
        model.addAttribute("tweet", new Tweet());
        return "tweet";
    }

    @PostMapping(path="/tweet")
    public String postTweet(@ModelAttribute Tweet tweet) {
        postTweet.post(tweet.getText());
        return "tweet";
    }

    @GetMapping(path = "/timed")
    public String getTimedForm(Model model) {
        model.addAttribute("timedTweet", new TimedTweet());
        return "timed";
    }

    @PostMapping(path="/timed")
    public String postTimed(@ModelAttribute TimedTweet tweet) {
        new PostTimedTweet(tweet.getHour(), tweet.getMinute(), tweet.getSecond(), twitterFactory, tweet.getText());
        return "timed";
    }

    @GetMapping(path = "/timeline")
    public String getTimelineForm(Model model) {
        model.addAttribute("timeLine", new TimeLine());
        return "timeline";
    }

    @PostMapping(path="/timeline")
    public String saveTimeline(@ModelAttribute TimeLine timeLine) {
        parseTimeline.getTimeLine(timeLine.getHandle(), twitterFactory, timeLine.getPage(), timeLine.getCount(), timeLine.isPutInDB());
        return "timeline";
    }
}
