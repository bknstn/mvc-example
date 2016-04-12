package by.academy.it.belotserkovsky.command;

import by.academy.it.belotserkovsky.command.constants.UserType;
import by.academy.it.belotserkovsky.daoServices.UserDAOService;
import by.academy.it.belotserkovsky.entity.User;
import by.academy.it.belotserkovsky.logic.LoginLogic;
import by.academy.it.belotserkovsky.managers.ConfigurationManager;
import by.academy.it.belotserkovsky.managers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Define type users and redirecting to the appropriate page
 * Created by Kostya on 09.04.2016.
 */
public class LoginCommand implements ActionCommand {

    private final String UID = "u_id";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private final int ONE_WEEK = 60*60*24*7;

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginLogic.getInstance().checkAdminLogin(login, pass)) {
            request.setAttribute("admin", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", UserType.ADMIN);
            page = ConfigurationManager.PATH_PAGE_ADMIN;
        } else if (LoginLogic.getInstance().checkUserLogin(login, pass)) {
            User user = UserDAOService.getInstance().getUserByLoginPass(login, pass);
            request.setAttribute("user", login);
            request.setAttribute("u_id", user.getId());
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", UserType.USER);
            Cookie c = new Cookie(UID, String.valueOf(user.getId()));
            c.setMaxAge(ONE_WEEK);
            response.addCookie(c);
            page = ConfigurationManager.PATH_PAGE_USER;
        }
        else {
            request.setAttribute("errorLoginPassMessage", MessageManager.MESSAGE_LOGIN_ERROR);
            request.getSession().setAttribute("userType", UserType.GUEST);
            page = ConfigurationManager.PATH_PAGE_LOGIN;
        }
        return page;
    }

}