package ua.training.restaurant.utils;

import ua.training.restaurant.config.SecurityConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    // Check if this request requires login.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = request.getServletPath();
        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    // Check if this 'request' has a suitable role?
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = request.getServletPath();

        Set<String> allRoles = SecurityConfig.getAllAppRoles();

        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
