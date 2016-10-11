package by.htp.teploukhava.testing.command;

import by.htp.teploukhava.testing.command.factory.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**Class contains command for return to main page */

public class ReturnMainCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session=request.getSession();
		Enumeration<String> listSessionParameter=session.getAttributeNames();
		while(listSessionParameter.hasMoreElements()){
			String nameParameter=listSessionParameter.nextElement();
			if(!nameParameter.equals("user")){
				// delete from session all attributes besides "user"
				session.removeAttribute("nameParameter"); 
			}
		}
		Enumeration<String> listRequestParameter=request.getAttributeNames();
		while(listRequestParameter.hasMoreElements()){
			// delete from request all attributes besides "user"
			request.removeAttribute(listRequestParameter.nextElement()); 
		}
		page="jsp/main.jsp";
		return page;
	}

}
