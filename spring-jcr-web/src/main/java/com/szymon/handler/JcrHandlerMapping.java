package com.szymon.handler;

import com.szymon.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;
import org.springmodules.jcr.JcrTemplate;

import javax.servlet.http.HttpServletRequest;

public class JcrHandlerMapping extends AbstractHandlerMapping {

    @Autowired
    private JcrTemplate jcrTemplate;

    private final Controller controller;

    public JcrHandlerMapping(Controller controller) {
        this.controller = controller;
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
        return jcrTemplate.itemExists("/" + RequestUtils.getRelativePath(request)) ? controller : null;
    }
}
