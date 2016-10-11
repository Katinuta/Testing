package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.telpoukhava.testing.entities.Question;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.exception.QuestionService;
import by.htp.teploukhava.testing.serviceimpl.AnswerService;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/** Class contatins command for deleting question and answer from test */

public class DeleteQuestionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null ;
		int questionId = Integer.parseInt(request.getParameter("idQuestion"));
		HttpSession session = request.getSession();
		Test test = (Test) session.getAttribute("test");
		try {
			QuestionService.getInstance().delete(questionId);
			// get new list of questions of the test
			List<Question> listQuestion = QuestionService.getInstance().findTestQuestions(test.getTestId());
			List<Answer> listAnswer = new ArrayList<>();
			Iterator<Question> it = listQuestion.iterator();

			while (it.hasNext()) {
				Question question = it.next();
				// get answer for question of test
				listAnswer.addAll(AnswerService.getInstance().findQuestionAnswers(question.getQuestionId()));

			}
			session.setAttribute("listQuestion", listQuestion);
			session.setAttribute("listAnswer", listAnswer);
			page = "jsp/contenttest.jsp";

		} catch (SQLException | ServiceException e) {
			e.printStackTrace();
		}
		return page;
	}

}
