package by.htp.teploukhava.testing.command;

import by.htp.teploukhava.testing.command.factory.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**Class contains page for creating test */

public class AddTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page="jsp/contenttest.jsp";

		return page;
	}

}
