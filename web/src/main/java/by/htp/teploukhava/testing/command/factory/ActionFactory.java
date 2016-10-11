package by.htp.teploukhava.testing.command.factory;

import javax.servlet.http.HttpServletRequest;

/**Class contains method for createing command */

public class ActionFactory {
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current;
		String action = request.getParameter("command");
		System.out.println(action);
		CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
		System.out.println(CommandEnum.valueOf(action.toUpperCase().toString()));
		current = currentEnum.getCurrentCommand();
		return current;
	}
}
