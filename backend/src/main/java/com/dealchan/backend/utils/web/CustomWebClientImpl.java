package com.dealchan.backend.utils.web;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 1/24/12
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope( value = "prototype")
public class CustomWebClientImpl implements CustomWebClient {

    private WebClient webClient;
    private String ipAddress;
    private String username;
    private String password;

    public WebClient getWebClient() {
        return webClient;
    }

    public CustomWebClientImpl() {
        initWebClient();
    }

    private void initWebClient() {
        try {
            webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
            webClient.setUseInsecureSSL(true);
            webClient.setJavaScriptEnabled(true);
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.setThrowExceptionOnScriptError(false);
            webClient.setThrowExceptionOnFailingStatusCode(false);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setIpAddress(String ipAddress) {
        CustomHttpWebConnection customHttpWebConnection = new CustomHttpWebConnection(this.webClient);
        customHttpWebConnection.setIpAddress(ipAddress);
        this.webClient.setWebConnection(customHttpWebConnection);
        this.ipAddress = ipAddress;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setBasicAuthentication(String username, String password) {
        this.username = username;
        this.password = password;

        if( username != null && password != null ) {
            DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
            provider.addCredentials(username, password);
            this.webClient.setCredentialsProvider(provider);
        }
    }
    
    public String getIpAddress() {
        return this.ipAddress;
    }

    public Page getPage( String url ) {
        try {
            //System.out.println(url);
            Page page = webClient.getPage(url);

            if(page.getWebResponse().getStatusCode() >= 400) {
                throw new FailingHttpStatusCodeException(page.getWebResponse());
            }
            return page;
        } catch (Exception ex ) {
            throw new RuntimeException(ex);
        }
    }

    public InputStream getPageAsStream( String url ) {
        Page page = getPage(url);
        return page.getWebResponse().getContentAsStream();
    }

    public Document getPageAsXml( String url ) {
        XmlPage page = (XmlPage) getPage(url);
        return page.getXmlDocument();
    }
    
    public String getPageAsString(String url) {
        Page page = getPage(url);
        return page.getWebResponse().getContentAsString();
    }
}
