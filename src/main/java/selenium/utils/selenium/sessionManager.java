package selenium.utils.selenium;

import org.openqa.selenium.Cookie;

import java.util.HashSet;
import java.util.Set;

public class sessionManager {

    private static final ThreadLocal<Set<Cookie>> cookies = new ThreadLocal<>();

    public static void saveCookies(Set<Cookie> sessionCookies){
        cookies.set(sessionCookies);
    }

    public static Set<Cookie> getCookies(){
        return cookies.get();
    }
    public static void clearSession() {
        cookies.remove();  // Remove session data when thread ends
    }
}
