package com.dealchan.backend.config.core;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.PrintWriter;

/**
* Created by IntelliJ IDEA.
* User: anbiniyar
* Date: 2/1/12
* Time: 5:41 PM
* To change this template use File | Settings | File Templates.
*/
public class ApplicationConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {


        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcConfig.class);

        try {
            PrintWriter writer = new PrintWriter("/tmp/fuckercibai");
            writer.println("FUCK YOU");
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/really/*");

    }
}
