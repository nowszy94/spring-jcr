package com.szymon.viewresolvers;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;


public class JsonViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setPrettyPrint(true);
        return mappingJackson2JsonView;
    }
}
