package com.szymon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmodules.jcr.JcrTemplate;

import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import java.util.Map;
import java.util.TreeMap;

@Service
public class NodeHelper {

    @Autowired
    private JcrTemplate jcrTemplate;

    public Map<String, String> getProperties(String absPath) {
        return (Map<String, String>) jcrTemplate.execute(session -> {
            Map<String, String> map = new TreeMap<>();
            PropertyIterator propertyIterator = session.getNode(absPath).getProperties();
            while (propertyIterator.hasNext()) {
                Property property = propertyIterator.nextProperty();
                map.put(property.getName(), property.getString());
            }
            return map;
        });
    }

    public String getProperty(String absPath, String propertyName) {
        String value = null;
        Map<String, String> properties = getProperties(absPath);
        if (properties.containsKey(propertyName)) {
            value = properties.get(propertyName);
        }
        return value;
    }

    public boolean exists(String absPath) {
        return jcrTemplate.itemExists(absPath);
    }
}
