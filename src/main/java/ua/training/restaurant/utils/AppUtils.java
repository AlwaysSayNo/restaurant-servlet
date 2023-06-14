package ua.training.restaurant.utils;

import ua.training.restaurant.entity.user.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {
    private static final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();
    private static int REDIRECT_ID = 0;

    // Save user info in Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    // Get user info from Session.
    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("loginedUser");
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(int redirectId) {
        return id_uri_map.get(redirectId);
    }
}
