package ua.training.restaurant.config;

import ua.training.restaurant.entity.user.Role;

import java.util.*;

public class SecurityConfig {
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // "User" role configuration.
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/user");
        urlPatterns1.add("/addfunds");
        urlPatterns1.add("/billpaying");
        urlPatterns1.add("/buyproduct");
        urlPatterns1.add("/concreteuserstatistic");
        urlPatterns1.add("/foodmenu");
        urlPatterns1.add("/paybill");
        urlPatterns1.add("/saveorder");
        urlPatterns1.add("/shoppingcart");
        urlPatterns1.add("/shoppingcartremovedish");
        urlPatterns1.add("/order");

        mapConfig.put(Role.USER.name(), urlPatterns1);

        // "Admin" role configuration.
        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/admin");
        urlPatterns2.add("/userstatistics");
        urlPatterns2.add("/concreteuserstatistic");
        urlPatterns2.add("/orderconfirmation");
        urlPatterns2.add("/acceptorder");
        urlPatterns2.add("/billmaking");
        urlPatterns2.add("/makebill");
        urlPatterns2.add("");
        urlPatterns2.add("/order");

        mapConfig.put(Role.ADMIN.name(), urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
