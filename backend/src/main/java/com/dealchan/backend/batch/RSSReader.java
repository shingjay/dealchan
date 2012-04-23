package com.dealchan.backend.batch;

import com.dealchan.backend.utils.web.CustomWebClient;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSReader implements ItemReader<SyndEntry> {

    private String url;


    private CustomWebClient webClient;

    private Document document;

    private int length;
    private int index;
    //private NodeList nodeList;
    private SyndFeedInput syndFeedInput;
    private SyndFeed feed;
    private List<SyndEntry> feedList;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public RSSReader(String url) {
        this.url = url;
    }

    @Autowired
    public void setWebClient(CustomWebClient webClient1) throws FeedException {
        this.webClient = webClient1;
        this.document = webClient.getPageAsXml(url);

        this.syndFeedInput = new SyndFeedInput();
        this.feed = syndFeedInput.build(document);
        this.feedList = feed.getEntries();

        this.index = 0;
        this.length = feedList.size();

        System.err.println("DEADB33F length: " + length);
    }


    @Override
    public SyndEntry read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(index == length || index == 5) {
            return null;
        }

        //System.out.println("READING NodeList : " + index + " : " + feedList.get(index));


        return feedList.get(index++);
    }
}
