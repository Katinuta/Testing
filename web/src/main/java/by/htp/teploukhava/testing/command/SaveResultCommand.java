package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Result;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ResultService;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**Class contains connamd for save result of the test*/

public class SaveResultCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int result = Integer.parseInt(request.getParameter("countRightQuestion"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Test test = (Test) session.getAttribute("test");
		Result res = new Result(test.getTestId(), user.getUserId(), result);
		try {
			ResultService.getInstance().create(res);
			session.removeAttribute("test");
			session.removeAttribute("subject");
			page = "jsp/main.jsp";
		} catch (SQLException | ServiceException e) {
			page="jsp/error.jsp";
		}
		return page;
	}

}
