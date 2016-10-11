package by.htp.teploukhava.testing.exception;

import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.telpoukhava.testing.entities.Question;
import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.AnswerDAOImpl;
import by.htp.teploukhava.testing.dao.impl.QuestionDAOImpl;
import by.htp.teploukhava.testing.managers.ConnectorDB;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 02.10.16.
 */
public class QuestionService implements AbstractService<Question> {
    private static QuestionService instance;

    private QuestionService(){}

   public static synchronized QuestionService getInstance(){
       if(instance==null){
           instance=new QuestionService();
       }
       return instance;
   }

    @Override
    public boolean create(Question entity) throws ServiceException, SQLException {
        boolean flag ;
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            QuestionDAOImpl.getInstance(connection).create(entity);
            connection.commit();
            flag=true;
        } catch (SQLException|PropertyVetoException | DAOException e) {
            connection.rollback();           
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return flag;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException, ServiceException {
        Connection connection =  null;
        try {
            connection=ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            AnswerDAOImpl.getInstance(connection).delete(id);
            QuestionDAOImpl.getInstance(connection).delete(id);
            connection.commit();
        } catch (SQLException|PropertyVetoException| DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
    }

    @Override
    public Question update(Question entity) {
        return null;
    }

    public List<Question> findTestQuestions(int testId) throws SQLException, ServiceException {

        List<Question> list;
        Connection connection =  null;
        try {
            connection=ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            list=QuestionDAOImpl.getInstance(connection).findTestQuestions(testId);
            connection.commit();
        } catch (SQLException|PropertyVetoException| DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return list;
    }

    public Question findQuestionByContentAndTest(String content, int testId) throws SQLException, ServiceException {
        Question question;
        Connection connection =  null;
        try {
            connection=ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            question=QuestionDAOImpl.getInstance(connection).findQuestionByContentAndTest(content, testId);
            connection.commit();
        } catch (SQLException|PropertyVetoException| DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return question;
    }

    public void updateContextQuestion(Question question, String content) throws ServiceException, SQLException {
        Connection connection =  null;
        try {
            connection=ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            QuestionDAOImpl.getInstance(connection).updateContextQuestion(question,content);
            connection.commit();
        } catch (SQLException|PropertyVetoException| DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
    }

    public void createQuestion(Question question, List<Answer> answerList) throws  ServiceException, SQLException {
        Connection connection =  null;
        try {
            connection=ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            QuestionDAOImpl.getInstance(connection).create(question);
            question=QuestionDAOImpl.getInstance(connection).findQuestionByContentAndTest(question.getContent(),question.getTestId());
            Iterator<Answer> it= answerList.iterator();
            while(it.hasNext()){
                Answer answer=it.next();
                answer.setQuestionId(question.getQuestionId());
                AnswerDAOImpl.getInstance(connection).create(answer);
            }
             connection.commit();
        } catch (SQLException|PropertyVetoException|  DAOException e) {
            connection.rollback();
            throw new  ServiceException(e.getMessage());
        } finally {
            connection.close();
        }

    }
}
