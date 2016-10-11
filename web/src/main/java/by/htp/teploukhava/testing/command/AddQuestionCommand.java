package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Class contains command for transition to create new question with answers for
 * new or old test
 */

public class AddQuestionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page ;
		HttpSession session = request.getSession();
		Enumeration<String> attrSession = session.getAttributeNames();
		Boolean hasEl = false;
		// check if session has attribute "test"
		if (attrSession.hasMoreElements()) {
			while (attrSession.hasMoreElements()) {
				String attr = attrSession.nextElement();
				if (attr.equals("test")) {
					hasEl = true;
					break;
				}
			}
		}
		// actions if create new test
		if (!hasEl) {
			Test test = new Test();
			String nameTest = request.getParameter("nametest");
			test.setName(nameTest);
			Subject subject = (Subject) session.getAttribute("subject");
			test.setSubjectId(subject.getSubjectId());
			try {
				TestService.getInstance().create(test);
				test = TestService.getInstance().findTestBySubjectIdAndName(subject.getSubjectId(), nameTest);
				session.setAttribute("test", test);
			} catch (SQLException | ServiceException e) {
				e.printStackTrace();
			}
		}
		page = "jsp/question.jsp";
		return page;
	}

}
