package by.htp.teploukhava.testing.command;

import by.htp.teploukhava.testing.command.factory.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**Class contatins page for creating new subject */

public class AddSubjectCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		return "jsp/addsubject.jsp";
	}

}
