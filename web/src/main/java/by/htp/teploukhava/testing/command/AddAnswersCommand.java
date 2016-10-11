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
import java.util.Enumeration;
import java.util.List;

/**Class contains command addition question with answers for new and old test */

public class AddAnswersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		Test test = (Test) session.getAttribute("test");
		String content = request.getParameter("question");
		Enumeration<String> attrSession=session.getAttributeNames();
		Boolean hasListQuestion=false;
		Boolean hasListAnswer=false;
		List<Answer> listAnswer = null;
		List<Question> listQuestion=null;
		if(attrSession.hasMoreElements()){
			while(attrSession.hasMoreElements()){
				String attr=attrSession.nextElement();
				//check if session has attribute "listQuestion" and "listAnswer"
				if(attr.equals("listQuestion")){
					hasListQuestion=true;
				}
				if(attr.equals("listAnswer")){
					hasListAnswer=true;
				}
			}
		}
		if(hasListAnswer&&hasListQuestion){
			//for old test
			listAnswer=(List<Answer>) session.getAttribute("listAnswer");
			listQuestion=(List<Question>) session.getAttribute("listQuestion");
		}else if(!hasListAnswer&&!hasListQuestion){
			//for new test
			listAnswer = new ArrayList<>();
			listQuestion = new ArrayList<>();
		}
		Question question = new Question();
		question.setTestId(test.getTestId());
		question.setContent(content);


		for (int i = 1; i <= 5; i++) {
			Answer answer = new Answer();
			answer.setContent(request.getParameter("answer" + i));
			//answer.setQuestionId(question.getQuestionId());
			String checkbox=request.getParameter("checkbox" + i);
			if (checkbox!=null&&checkbox.equals("on")) {
				answer.setRightAnswer(true);
			} else {
				answer.setRightAnswer(false);
			}
			listAnswer.add(answer);//addition answer to question
		}

		try {
			QuestionService.getInstance().createQuestion(question,listAnswer);
			question= QuestionService.getInstance().findQuestionByContentAndTest(content,test.getTestId());
			listQuestion.add(question); //addition new question
			session.setAttribute("listQuestion", listQuestion);
			listAnswer= AnswerService.getInstance().findQuestionAnswers(question.getQuestionId());
			session.setAttribute("listAnswer", listAnswer);
			page = "jsp/contenttest.jsp";
		} catch (ServiceException |SQLException e) {
			page="jsp/error.jsp";
		}
		return page;
	}

}
