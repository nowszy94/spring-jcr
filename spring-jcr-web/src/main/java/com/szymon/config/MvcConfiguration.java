package com.szymon.config;

import com.szymon.controller.JcrBasedController;
import com.szymon.handler.JcrHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public JcrHandlerMapping jcrHandlerMapping(JcrBasedController jcrBasedController) {
        JcrHandlerMapping jcrHandlerMapping = new JcrHandlerMapping(jcrBasedController);
        jcrHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return jcrHandlerMapping;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON);
    }
}
