package com.dealchan.backend.batch;

import com.dealchan.backend.utils.web.CustomWebClient;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSReader implements ItemReader<Node> {

    private String url;


    private CustomWebClient webClient;

    private Document document;

    private int length;
    private int index;
    private NodeList nodeList;

    public RSSReader(String url) {
        this.url = url;
    }

    @Autowired
    public void setWebClient(CustomWebClient webClient1) {
        this.webClient = webClient1;
        this.document = webClient.getPageAsXml(url);
        this.nodeList =  document.getElementsByTagName("item");
        this.index = 0;
        this.length = nodeList.getLength();

    }


    @Override
    public Node read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("READING NodeList : " + index + " : " + nodeList.item(index));
        if(index == length) {
            return null;
        }

        return nodeList.item(index++);
    }
}
