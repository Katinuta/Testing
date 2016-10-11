package by.htp.teploukhava.testing.serviceimpl;


import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.AnswerDAOImpl;
import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.ConnectorDB;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 02.10.16.
 */
public class AnswerService implements AbstractService<Answer> {
    private static AnswerService instance;

    private AnswerService(){}

    public static synchronized AnswerService getInstance(){
        if(instance==null){
            instance=new AnswerService();
        }
        return instance;
    }

    @Override
    public boolean create(Answer entity) throws ServiceException {
        return false;
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

    public List<Answer> findQuestionAnswers(int questionId) throws ServiceException, SQLException {
        Connection connection =  null;
        List<Answer> answerList;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            answerList= AnswerDAOImpl.getInstance(connection).findQuestionAnswers(questionId);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());

        }finally {
            connection.close();
        }
        return answerList;
    }

    public void updateAnswer(Answer answer, String content, Boolean rightAnswer) throws SQLException, ServiceException {
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            AnswerDAOImpl.getInstance(connection).updateAnswer(answer, content, rightAnswer);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
    }

}
