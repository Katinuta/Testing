package by.htp.teploukhava.testing.command;

import by.htp.teploukhava.testing.command.factory.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Admin on 28.09.16.
 */
public class ChooseLocalCommand implements ActionCommand {

    public String execute(HttpServletRequest request){

        String page="login.jsp";
        String locale=request.getParameter("locale");
        HttpSession session=request.getSession();
        session.setAttribute("locale",locale);
        return page;
    }

}
