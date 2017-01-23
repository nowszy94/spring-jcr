package com.szymon.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getRelativePath(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        int endIndex = requestURI.indexOf(".");
        return requestURI.substring(1, endIndex < 0 ? requestURI.length() : endIndex);
    }
}
