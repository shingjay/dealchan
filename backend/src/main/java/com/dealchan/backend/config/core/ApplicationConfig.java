package com.dealchan.backend.config.core;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

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

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        servletContext.addListener( new ContextLoaderListener(context));
        FilterRegistration.Dynamic registration = servletContext.addFilter("HiddenHttpMethodFilter", HiddenHttpMethodFilter.class);

        String name = "dispatcher";
        registration.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), false, name);



    }
}
