package com.szymon.config;

import com.szymon.viewresolvers.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class ViewResolversConfiguration {

    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }

}
