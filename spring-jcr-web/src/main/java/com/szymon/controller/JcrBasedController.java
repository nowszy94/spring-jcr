package com.szymon.controller;

import com.szymon.utils.NodeUtils;
import com.szymon.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springmodules.jcr.JcrTemplate;

import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class JcrBasedController extends AbstractController {

    @Autowired
    private JcrTemplate jcrTemplate;

    @Override
    protected ModelAndView handleRequestInternal(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(resolveViewName(request));
        modelAndView.addAllObjects(getProperties(request));
        return modelAndView;
    }

    private String resolveViewName(final HttpServletRequest request) {
        return jcrTemplate.execute(session -> {
            Node node = session.getNode(RequestUtils.getAbsoultePath(request));
            return NodeUtils.getProperty(node, "viewName");
        }, String.class);
    }

    private Map<String, Object> getProperties(final HttpServletRequest request) {
        return (Map<String, Object>) jcrTemplate.execute(session -> {
            Node node = session.getNode(RequestUtils.getAbsoultePath(request));
            return NodeUtils.getProperties(node);
        });
    }
}
