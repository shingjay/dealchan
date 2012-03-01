package com.dealchan.backend.utils.web;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import org.w3c.dom.Document;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 1/24/12
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CustomWebClient {

    public String getIpAddress();
    public void setIpAddress(String ipAddress);

    public WebClient getWebClient();
    public Page getPage(String url);
    public void setBasicAuthentication(String username, String password);
    public String getPageAsString(String url);
    public InputStream getPageAsStream(String url);
    public Document getPageAsXml(String url);
}
