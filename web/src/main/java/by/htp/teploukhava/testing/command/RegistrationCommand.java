package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**Class contains command for registration new student*/

public class RegistrationCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String login=request.getParameter("login");
		String password= DigestUtils.md5Hex(request.getParameter("password")+"java");
		User user=new User();
		user.setAccess(false);
		user.setName(name);
		user.setSurname(surname);
		user.setPassword(password);
		user.setLogin(login);
		try {
			boolean flag = UserService.getInstance().create(user);
			if(flag){
				page="login.jsp";
			}else {
				request.setAttribute("errorRegMessage", "Try to book again");
				page = "jsp/registration.jsp";
			}
		} catch (SQLException| ServiceException e) {
			e.printStackTrace();
		}
		return page;
	}

}
