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

/**Class contains command for getting the test questions with answers for changing */

public class ChangeTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		HttpSession session=request.getSession();
		String number=request.getParameter("button"); //get choose test id
		int testId=Integer.valueOf(number);
		Test test=null;
		List<Question> listQuestion;
		List<Answer> listAnswer=new ArrayList<>();
		try {
			test= TestService.getInstance().findTestByTestId(testId);
			session.setAttribute("test", test);
			//get list of questions of test
			listQuestion= QuestionService.getInstance().findTestQuestions(test.getTestId());
			session.setAttribute("listQuestion", listQuestion);
			Iterator<Question> it=listQuestion.iterator();

			while(it.hasNext()){
				Question question=(Question) it.next();
				//get answers for question of test
				List<Answer> list= AnswerService.getInstance().findQuestionAnswers(question.getQuestionId());
				listAnswer.addAll(list);
			}
			session.setAttribute("listAnswer", listAnswer);
			session.setAttribute("change", true);
			page = "jsp/contenttest.jsp";
		} catch (ServiceException |SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

}
