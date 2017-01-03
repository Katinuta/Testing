package by.htp.teploukhava.testing.serviceimpl;


import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.AnswerDAOImpl;
import by.htp.teploukhava.testing.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 02.10.16.
 */
@Service
@Transactional
public class AnswerService implements AbstractService<Answer> {
    private AnswerDAOImpl answerDAOImpl;

    public AnswerService(){}
    @Autowired
    public AnswerService(AnswerDAOImpl answerDAOImpl){
        this.answerDAOImpl =answerDAOImpl;
    }

    @Override
    public boolean create(Answer entity)  {
        boolean flag = false;

        return flag;
    }

    @Override
    public List<Answer> findAll()  {
        return null;
    }

    @Override
    public void delete(int id) {}

    @Override
    public Answer update(Answer entity) {
        return null;
    }

    @Override
    public Answer find(int id) throws ServiceException {
        return null;
    }

    public List<Answer> findQuestionAnswers(int questionId) throws ServiceException {
        List<Answer> answerList= answerDAOImpl.findQuestionAnswers(questionId);
        return answerList;
    }

    public void updateAnswer(Answer answer, String content, Boolean rightAnswer) throws ServiceException {
//        Connection connection =  null;
//        try {
//            connection= ConnectorDB.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            AnswerDAOImpl.getInstance(connection).updateAnswer(answer, content, rightAnswer);
//            connection.commit();
//        } catch (SQLException |PropertyVetoException | DAOException e) {
//            connection.rollback();
//            throw new ServiceException(e.getMessage());
//        } finally {
//            connection.close();
//        }
    }

}
