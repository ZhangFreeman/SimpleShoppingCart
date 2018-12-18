package utils;

import javax.servlet.http.HttpServletRequest;

public class MyUtil {

    /**
     * Extract base URL from request
     * @param request
     * @return base URL
     */
    public static String extractBaseURL (HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
        return base;
    }

}
