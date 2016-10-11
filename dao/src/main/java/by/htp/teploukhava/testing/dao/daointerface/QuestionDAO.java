package by.htp.teploukhava.testing.dao.daointerface;


import by.htp.telpoukhava.testing.entities.Question;
import by.htp.teploukhava.testing.exception.DAOException;

import java.util.List;

/** Interface contains methods for access to table Question */

public interface QuestionDAO extends AbstractDAO<Question> {

	public Question findQuestionByContentAndTest(String content, int testId) throws DAOException;

	public int updateContextQuestion(Question question, String content)throws DAOException ;

    public List<Question> findTestQuestions(int testId)throws DAOException ;
}
