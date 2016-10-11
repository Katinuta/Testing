package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Subject;
import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.SubjectService;
import by.htp.teploukhava.testing.serviceimpl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Class contains command for getting all user subjects*/

public class SubjectCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		Boolean role=user.isAccess();
		List<Subject> list=new ArrayList<>();
		try {
			if(role){
				list= SubjectService.getInstance().findAll();		//find all subjects for admin
			}else if(!role){
				list= UserService.getInstance().findUserSubject(user.getUserId());  //find all subject for student
			}
			request.setAttribute("listSubject", list);  //set list of subject in the request
			page="jsp/subject.jsp";
		} catch (ServiceException |SQLException e) {
			page="jsp/error.jsp";
		}
		return page;
	}

}
