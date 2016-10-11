package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** Class contatins for creating and save new subject */

public class SaveSubjectCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page ;
		String name = request.getParameter("subject");
		Subject subject=new Subject();
		subject.setName(name);
		try {
			SubjectService.getInstance().create(subject);
			page = "jsp/main.jsp";
		} catch (SQLException | ServiceException e) {
			page="jsp/error.jsp";
		}
		return page;
	}

}
