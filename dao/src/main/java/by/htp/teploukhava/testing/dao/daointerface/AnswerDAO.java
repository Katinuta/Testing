package by.htp.teploukhava.testing.dao.daointerface;

import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.exception.DAOException;

import java.util.List;

/** Interface contains methods for access to table Answer */

public interface AnswerDAO extends AbstractDAO<Answer> {

	public List<Answer> findQuestionAnswers(int questionId) throws DAOException;

	public int updateAnswer(Answer answer, String content, Boolean rightAnswer) throws DAOException;


}
