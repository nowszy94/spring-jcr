package com.szymon.handler;

import com.szymon.bind.RequestedNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;

public class JcrHandlerMapping extends AbstractHandlerMapping {

    @Autowired
    private RequestedNode requestedNode;

    private final Controller controller;

    public JcrHandlerMapping(Controller controller) {
        this.controller = controller;
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
        return requestedNode.exists() ? controller : null;
    }
}
