package by.htp.teploukhava.testing.command.factory;

import javax.servlet.http.HttpServletRequest;

/** Intarface executes command */

public interface ActionCommand {
	String execute(HttpServletRequest request);

	/**
     * Created by Admin on 02.10.16.
     */

}
