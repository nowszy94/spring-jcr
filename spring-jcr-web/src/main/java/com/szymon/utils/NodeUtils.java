package com.szymon.utils;

import org.springmodules.jcr.JcrTemplate;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import java.util.Map;
import java.util.TreeMap;

public class NodeUtils {

    public static Map<String, Object> getProperties(Node node) {
        Map<String, Object> propertiesMap = new TreeMap<>();
        try {
            PropertyIterator properties = node.getProperties();
            while (properties.hasNext()) {
                Property property = properties.nextProperty();
                propertiesMap.put(property.getName(), property.getValue().getString());
            }
        } catch (RepositoryException e) {
        }
        return propertiesMap;
    }

    public static String getProperty(Node node, String propertyName) {
        return getProperty(node, propertyName, String.class);
    }

    public static <T> T getProperty(Node node, String propertyName, Class<T> clazz) {
        Map<String, Object> properties = getProperties(node);
        T t = null;
        if (properties.containsKey(propertyName)) {
            t = (T) getProperties(node).get(propertyName);
        }
        return t;
    }

    public static Node getNode(JcrTemplate jcrTemplate, String absPath) {
        return (Node) jcrTemplate.getItem(absPath);
    }

}
