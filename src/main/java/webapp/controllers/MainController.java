package webapp.controllers;

import credentials.ConfigMaker;
import operations.get_tweets.GetStream;
import operations.get_tweets.ParseTimeLine;
import operations.get_tweets.SearchByExpression;
import operations.posting.PostTimedTweet;
import operations.posting.PostTweet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import webapp.models.*;

@Controller
@RequestMapping(path="/twitter")
public class MainController {
    private ConfigMaker configMaker;
    private ConfigurationBuilder configurationBuilder;
    private Configuration config;
    private TwitterFactory twitterFactory;
    private PostTweet postTweet;
    private ParseTimeLine parseTimeline;
    private SearchByExpression searchByExpression;
    private TwitterStream twitterStream;

    public MainController() {
        this.configMaker = new ConfigMaker();
        this.configurationBuilder = configMaker.buildConfig();
        this.config = configurationBuilder.build();
        this.twitterFactory = new TwitterFactory(config);
        this.postTweet = new PostTweet(twitterFactory);
        this.parseTimeline = new ParseTimeLine();
        this.searchByExpression = new SearchByExpression();
        this.twitterStream = new TwitterStreamFactory(config).getInstance();
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
    public String postTimeline(@ModelAttribute TimeLine timeLine) {
        parseTimeline.getTimeLine(timeLine.getHandle(), twitterFactory, timeLine.getPage(), timeLine.getCount(), timeLine.isPutInDB());
        return "timeline";
    }

    @GetMapping(path = "/expression")
    public String getExpressionForm(Model model) {
        model.addAttribute("expression", new Expression());
        return "expression";
    }

    @PostMapping(path="/expression")
    public String postExpression(@ModelAttribute Expression expression) {
        searchByExpression.search(expression.getExpression(), twitterFactory, expression.getCount(), expression.isPutInDB());
        return "expression";
    }

    @GetMapping(path = "/stream")
    public String getStreamForm(Model model) {
        model.addAttribute("stream", new Stream());
        return "stream";
    }

    @PostMapping(path="/stream")
    public String postExpression(@ModelAttribute Stream stream) {
        GetStream getStream = new GetStream();
        getStream.stream(twitterStream, stream.getFilterWord(), stream.getLanguage(),
                stream.isPutInDB(), stream.isUseFiltering(), stream.isUploadPictures(),
                stream.getStreamLength());
        return "stream";
    }
}
