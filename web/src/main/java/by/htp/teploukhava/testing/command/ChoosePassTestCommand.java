package by.htp.teploukhava.testing.command;


import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.telpoukhava.testing.entities.Question;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.exception.QuestionService;
import by.htp.teploukhava.testing.serviceimpl.AnswerService;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**Class contatins command for getiing questions of the test with answer to pass*/

public class ChoosePassTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testId = Integer.parseInt(request.getParameter("button"));
		HttpSession session = request.getSession();
		Test test;
		try {
			test = TestService.getInstance().findTestByTestId(testId);
			session.setAttribute("test", test);
			//find questions of the test
			List<Question> listQuestion = QuestionService.getInstance().findTestQuestions(testId);
			List<Answer> listAnswer = new ArrayList<>();
			Iterator<Question> it = listQuestion.iterator();
			while (it.hasNext()) {
				Question question = it.next();
				//find answers for the question
				listAnswer.addAll(AnswerService.getInstance().findQuestionAnswers(question.getQuestionId()));
			}
			session.setAttribute("listAnswer", listAnswer);
			session.setAttribute("listQuestion", listQuestion);
			page = "jsp/passtest.jsp";

		} catch (ServiceException |SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

}
