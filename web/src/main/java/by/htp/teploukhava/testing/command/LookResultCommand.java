package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Result;
import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ResultService;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**Class contains command for show result of the test for student*/

public class LookResultCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int testId = Integer.parseInt(request.getParameter("testId"));
		try {
			Result result = ResultService.getInstance().findResultByTestUser(testId, user.getUserId());
			request.setAttribute("result", result);
			page = "jsp/showresult.jsp";
		} catch (ServiceException |SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

}
