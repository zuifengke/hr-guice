package com.zeng.servlet;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.io.File;
import java.util.EnumSet;

public class HttpServerJetty {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                binder().requireExplicitBindings();
                install(new TestResourceModule());
                install(new TestServletModule());
                bind(GuiceFilter.class);
            }
        });

        Server server = new Server(8090);

        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.setContextPath("/");
        servletHandler.addServlet(new ServletHolder(new InvalidRequestServlet()), "/*");
        FilterHolder guiceFilter = new FilterHolder(injector.getInstance(GuiceFilter.class));
        servletHandler.addFilter(guiceFilter, "/*", EnumSet.allOf(DispatcherType.class));

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        String webappDirLocation = "src/main/webapp/";
        String absolutePath = new File(webappDirLocation).getAbsolutePath();
        resourceHandler.setResourceBase(absolutePath);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resourceHandler, servletHandler, new DefaultHandler() });

        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
