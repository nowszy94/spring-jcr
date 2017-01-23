package com.szymon.utils;

import javax.jcr.*;
import java.util.HashMap;
import java.util.Map;

public class NodeUtils {

    public static Map<String, Object> getProperties(Node node) {
        Map<String, Object> propertiesMap = new HashMap<>();
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

    public static <T> T getProperty(Node node, String propertyName, Class<T> clazz) {
        return (T) getProperties(node).get(propertyName);
    }

    public static Node getNode(Session session, String relPath) {
        try {
            return session.getRootNode().getNode(relPath);
        } catch (RepositoryException e) {
            return null;
        }
    }

}
