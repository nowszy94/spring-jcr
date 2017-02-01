package com.szymon.bind;

import com.szymon.utils.NodeHelper;
import com.szymon.utils.RequestUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.jackrabbit.JcrConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestedNode {

    private HttpServletRequest request;

    private NodeHelper nodeHelper;

    private JcrTemplate jcrTemplate;

    private String primaryType;

    private String absPath;

    private String relativePath;

    private Map<String, String> properties;

    @Autowired
    public RequestedNode(HttpServletRequest request, NodeHelper nodeHelper, JcrTemplate jcrTemplate) {
        this.request = request;
        this.nodeHelper = nodeHelper;
        this.jcrTemplate = jcrTemplate;
    }

    @PostConstruct
    public void setUp() {
        initPath();
        if (nodeHelper.exists(absPath)) {
            initProperties();
            initPrimaryType();
        }
    }

    private void initPath() {
        this.absPath = RequestUtils.getAbsoultePath(request);
        this.relativePath = RequestUtils.getRelativePath(request);
    }

    private void initProperties() {
        this.properties = nodeHelper.getProperties(absPath);
    }

    private void initPrimaryType() {
        this.primaryType = properties.get(JcrConstants.JCR_PRIMARYTYPE);
    }

    public <T> T execute(JcrCallback jcrCallback, Class<T> clazz) {
        return jcrTemplate.execute(jcrCallback, clazz);
    }

    public <T> T execute(JcrCallback jcrCallback, boolean exposeNativeSession, Class<T> clazz) {
        return jcrTemplate.execute(jcrCallback, exposeNativeSession, clazz);
    }

    public Object execute(JcrCallback jcrCallback) {
        return execute(jcrCallback, Object.class);
    }

    public Object execute(JcrCallback jcrCallback, boolean exposeNativeSession) {
        return jcrTemplate.execute(jcrCallback, exposeNativeSession);
    }

    public boolean exists() {
        return nodeHelper.exists(absPath);
    }

    public String getProperty(String propertyName) {
        return nodeHelper.getProperty(this.absPath, propertyName);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getAbsPath() {
        return absPath;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
