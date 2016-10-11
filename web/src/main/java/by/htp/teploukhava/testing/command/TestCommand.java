package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.SubjectService;
import by.htp.teploukhava.testing.serviceimpl.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


/** Class contains command for getting list of test by choose subject */

public class TestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		String number = request.getParameter("but");
		int id = Integer.valueOf(number); // get id choose subject
		List<Test> list;
		try {
			Subject subject = SubjectService.getInstance().findSubjectById(id);
			session.setAttribute("subject", subject);
			list = TestService.getInstance().findTestBySubjectId(id);
			//set in request list of tests by subject
			request.setAttribute("testList", list);
			page = "jsp/test.jsp";
		} catch (SQLException | ServiceException e) {
			page="jsp/error.jsp";
		}
		return page;
	}

}
