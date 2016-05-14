package by.academy.it.belotserkovsky.command;

import by.academy.it.belotserkovsky.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by K.Belotserkovsky
 */
public class CreateBidCommand implements ActionCommand {

    /**
     * @param request
     * @param response
     * @return String page
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.PATH_PAGE_FORM_BID;
        return page;
    }
}