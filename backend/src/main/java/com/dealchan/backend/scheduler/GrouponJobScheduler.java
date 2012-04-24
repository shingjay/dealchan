package com.dealchan.backend.scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: yingzhe
 * Date: 4/23/12
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrouponJobScheduler {

    public void schedule()
    {
        try {
            // Construct data
            Calendar now = Calendar.getInstance();
            System.out.println(((Long)(now.getTimeInMillis())).toString());
            String data = URLEncoder.encode("launch", "UTF-8") + "=" + URLEncoder.encode("Launch", "UTF-8");
            data += "&" + URLEncoder.encode("jobParameters", "UTF-8") + "=time=" + URLEncoder.encode(((Long)(now.getTimeInMillis())).toString(), "UTF-8");
            data += "&" + URLEncoder.encode("origin", "UTF-8") + "=" + URLEncoder.encode("job", "UTF-8");

            // Send data
            URL url = new URL("http://localhost:8080/backend-1.0/batch/jobs/grouponKlang");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
