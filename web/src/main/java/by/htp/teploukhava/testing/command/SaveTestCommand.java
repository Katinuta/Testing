package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.*;

import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


/**Class contains command for changing questions and answers of test*/

public class SaveTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		Subject subject = (Subject) session.getAttribute("subject");
		//actions if test change
		try {
			if (session.getAttribute("change") != null) {
				Test test = (Test) session.getAttribute("test");
				test.setName(request.getParameter("changeName"));
				List<Question> listQuestion = (List<Question>) session.getAttribute("listQuestion");
				List<Answer> listAnswer = (List<Answer>) session.getAttribute("listAnswer");
				Iterator<Question> itQuestion = listQuestion.iterator();

				while (itQuestion.hasNext()) {
					Question question = itQuestion.next();
					String content = request.getParameter(Integer.toString(question.getQuestionId()));
					System.out.println(content);
					question.setContent(content);
				}
				Iterator<Answer> itAnswer = listAnswer.iterator();

				while (itAnswer.hasNext()) {
					Answer answer = itAnswer.next();
					String content = request.getParameter(Integer.toString(answer.getAnswerId()));
					System.out.println(content);
					answer.setContent(content);
					String checkbox = request.getParameter("checkbox" + Integer.toString(answer.getAnswerId()));
					Boolean rightAnswer = false;
					if (checkbox != null && checkbox.equals("on")) {
						rightAnswer = true;
					}
					System.out.println(rightAnswer);
					answer.setRightAnswer(rightAnswer);
				}
				TestService.getInstance().updateTest(test, listQuestion, listAnswer);
				session.removeAttribute("change");
			}
				//actions for save test cteating or changing
				session.removeAttribute("listAnswer");
				session.removeAttribute("listQuestion");
				session.removeAttribute("test");

				//get new list of test by subject
				List<Test> list = TestService.getInstance().findTestBySubjectId(subject.getSubjectId());
				request.setAttribute("testList", list);
			} catch(ServiceException | SQLException e){
				e.printStackTrace();
			}
			page = "jsp/test.jsp";

		return page;
	}

}
