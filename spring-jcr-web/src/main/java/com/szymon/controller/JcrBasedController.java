package com.szymon.controller;

import com.szymon.bind.RequestedNode;
import com.szymon.utils.NodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JcrBasedController extends AbstractController {

    @Autowired
    private RequestedNode requestedNode;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(resolveViewName());
        modelAndView.addAllObjects(requestedNode.getProperties());
        return modelAndView;
    }

    private String resolveViewName() {
        return NodeUtils.getProperty(requestedNode.getNode(), "viewName", String.class);
    }
}
