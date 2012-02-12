package com.dealchan.utils.web;

import com.gargoylesoftware.htmlunit.HttpWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.params.HttpParams;

import java.net.InetAddress;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 1/24/12
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomHttpWebConnection extends HttpWebConnection {
    
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public CustomHttpWebConnection(WebClient webClient) {
        super(webClient);
    }

    @Override
    protected AbstractHttpClient createHttpClient() {
        AbstractHttpClient client = super.createHttpClient();
        try {

            if (ipAddress != null) {
                HttpParams params = client.getParams();
                params.setParameter(ConnRoutePNames.LOCAL_ADDRESS,
                        InetAddress.getByName(ipAddress));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
