package controllers;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class PathUtil implements Serializable {

   static public String getElementOfPathArray(String pathInfo, int elementNumber) {
        return splitPathBySlashes(pathInfo)[elementNumber];
    }

    private static String[] splitPathBySlashes(String pathInfo) {
        return pathInfo.split("/");
    }

    static public String getLastPartOfPath(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        int lastPath = requestUri.lastIndexOf('/');
        String path = requestUri.substring(lastPath);
        return path;
    }
}