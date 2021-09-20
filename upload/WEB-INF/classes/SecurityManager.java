import javax.servlet.http.*;
import java.io.IOException;

public class SecurityManager {
    private static final String usernameKey = "user_name";
    public static boolean isAuthenticated(HttpServletRequest request) {
        return SecurityManager.getUserName(request) != null;
    }

    public static String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute(usernameKey);
        return username;
    }

    public static void setAuthenticated(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute(usernameKey, username);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(usernameKey);
    }

    public static void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("./index.html");
    }

    public static boolean authenticate (HttpServletRequest request, String name, String password) {
        DataManager dataManager = new DataManager();
        UserManager userManger = new UserManager(dataManager);
        if(userManger.isUserExist(name, password)) {
            SecurityManager.setAuthenticated(request, name);
            return true;
        }
        return false;
    }

}