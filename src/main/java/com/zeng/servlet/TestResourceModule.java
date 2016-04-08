package com.zeng.servlet;

import com.google.inject.servlet.ServletModule;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.HashMap;
import java.util.Map;


public class TestResourceModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(TestResource.class);

        ResourceConfig jerseyConfig = new ResourceConfig();
        jerseyConfig.packages("com.zeng.servlet");
        jerseyConfig.register(MultiPartFeature.class);
        ServletContainer servletContainer = new ServletContainer(jerseyConfig);

        serve("/api/*").with(servletContainer);
    }
}
