package com.szymon.controller;

import com.szymon.bind.RequestedNode;
import com.szymon.constants.SpringJcrConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JcrBasedController extends AbstractController {

    @Autowired
    private RequestedNode requestedNode;

    @Override
    protected ModelAndView handleRequestInternal(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(resolveViewName());
        modelAndView.addAllObjects(requestedNode.getProperties());
        return modelAndView;
    }

    private String resolveViewName() {
        return requestedNode.getProperty(SpringJcrConstants.VIEWNAME_PROPERTY_NAME);
    }
}
