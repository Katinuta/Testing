package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class DeleteTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		HttpSession session=request.getSession();
		int testId=Integer.parseInt(request.getParameter("button"));
		Subject subject=(Subject) session.getAttribute("subject");
		try {
			TestService.getInstance().delete(testId);
			List<Test> testList=TestService.getInstance().findTestBySubjectId(subject.getSubjectId());
			session.setAttribute("testList", testList);
			page="jsp/test.jsp";
		} catch (SQLException | ServiceException e) {
			e.printStackTrace();
		}
		return page;
	}

}
