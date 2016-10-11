package by.htp.teploukhava.testing.serviceimpl;


import by.htp.telpoukhava.testing.entities.Answer;
import by.htp.telpoukhava.testing.entities.Question;
import by.htp.telpoukhava.testing.entities.Test;
import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.*;

import by.htp.teploukhava.testing.exception.DAOException;
import by.htp.teploukhava.testing.managers.ConnectorDB;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Admin on 02.10.16.
 */
public class TestService implements AbstractService<Test> {

    private static TestService instance;

    private TestService(){}

    public static synchronized TestService getInstance(){
        if(instance==null){
            instance=new TestService();
        }
        return instance;
    }


    @Override
    public boolean create(Test entity) throws SQLException, ServiceException {
       boolean flag=false;
       Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            TestDAOImpl.getInstance(connection).create(entity);
            flag=true;
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return flag;
    }

    @Override
    public List<Test> findAll()  {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException, ServiceException {
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            List<Question> questionList= QuestionDAOImpl.getInstance(connection).findTestQuestions(id);
            Iterator<Question> it=questionList.iterator();
            while (it.hasNext()){
                Question question=it.next();
                AnswerDAOImpl.getInstance(connection).delete(question.getQuestionId());
                QuestionDAOImpl.getInstance(connection).delete(question.getQuestionId());
            }
            TestDAOImpl.getInstance(connection).delete(id);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
    }

    @Override
    public Test update(Test entity) {
        return null;
    }

    public List<Test> findTestBySubjectId(int subjectId) throws SQLException, ServiceException {
        Connection connection =  null;
        List<Test> listTest;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            listTest= TestDAOImpl.getInstance(connection).findTestBySubjectId(subjectId);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return listTest;
    }

    public Test findTestBySubjectIdAndName(int subjectId,String name) throws SQLException, ServiceException {
        Connection connection =  null;
        Test test;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            test= TestDAOImpl.getInstance(connection).findTestBySubjectIdAndName(subjectId, name);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return test;
    }

    public Test findTestByTestId(int testId) throws ServiceException, SQLException {
        Connection connection =  null;
        Test test;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            test= TestDAOImpl.getInstance(connection).findTestByTestId(testId);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }
        return test;
    }


    public void updateNameTestById(Test test, String name) throws ServiceException, SQLException {
        Connection connection =  null;
        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            TestDAOImpl.getInstance(connection).updateNameTestById(test,name);
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }

    }
    public void updateTest(Test test, List<Question> questionList,List<Answer> answerList) throws SQLException, ServiceException {
        Connection connection =  null;

        try {
            connection= ConnectorDB.getInstance().getConnection();
            connection.setAutoCommit(false);
            TestDAOImpl.getInstance(connection).update(test);
            Iterator<Question> itQuestion=questionList.iterator();

            while (itQuestion.hasNext()){
                Question question =itQuestion.next();
                QuestionDAOImpl.getInstance(connection).update(question);
                Iterator<Answer> itAnswer=answerList.iterator();

                while (itAnswer.hasNext()){
                    Answer answer=itAnswer.next();
                    if(question.getQuestionId()==answer.getQuestionId()){
                        AnswerDAOImpl.getInstance(connection).update(answer);
                    }
                }
            }
            connection.commit();
        } catch (SQLException |PropertyVetoException | DAOException e) {
            connection.rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            connection.close();
        }

    }
}
