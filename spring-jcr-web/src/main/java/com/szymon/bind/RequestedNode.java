package com.szymon.bind;

import com.szymon.utils.NodeUtils;
import com.szymon.utils.RequestUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestedNode {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Session session;

    private Node node;

    private String path;

    private Map<String, Object> properties;

    @PostConstruct
    public void setUp() {
        initPath();
        initNode();
        initProperties();
    }

    private void initNode() {
        this.node = NodeUtils.getNode(session, path.substring(1));
    }

    private void initPath() {
        this.path = "/" + RequestUtils.getRelativePath(request);
    }

    private void initProperties() {
        this.properties = new HashMap<>();
        this.properties.putAll(NodeUtils.getProperties(node));
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
