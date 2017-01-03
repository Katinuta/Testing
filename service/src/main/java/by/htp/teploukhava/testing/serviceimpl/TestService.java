package by.htp.teploukhava.testing.serviceimpl;


import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.AnswerDAOImpl;
import by.htp.teploukhava.testing.dao.impl.QuestionDAOImpl;
import by.htp.teploukhava.testing.dao.impl.TestDAOImpl;
import by.htp.teploukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.entities.Question;
import by.htp.teploukhava.testing.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Admin on 02.10.16.
 */
@Service
@Transactional
public class TestService implements AbstractService<Test> {
    @Autowired
    private TestDAOImpl testDAOImpl;
    @Autowired
    private QuestionDAOImpl questionDAOImpl;
    @Autowired
    private AnswerDAOImpl answerDAOImpl;

    public TestService(){}

    public TestService(TestDAOImpl testDAOImpl){
        this.testDAOImpl=testDAOImpl;
    }


    @Override
    public boolean create(Test entity)  {
        boolean flag=false;
        testDAOImpl.create(entity);
        flag=true;
        return flag;
    }

    @Override
    public List<Test> findAll()  {
        return null;
    }

    @Override
    public void delete(int id) {
        testDAOImpl.delete(id);
    }

    @Override
    public Test update(Test entity)  {
        testDAOImpl.update(entity);
        return entity;
    }

    @Override
    public Test find(int id)  {
        return testDAOImpl.find(id);
    }

    public List<Test> findTestBySubjectId(int subjectId)   {
        List<Test> listTest=testDAOImpl.findTestBySubjectId(subjectId);
        return listTest;
    }

    public Test findTestBySubjectIdAndName(int subjectId,String name) {
       Test test=null;
        return test;
    }


    public void updateNameTestById(Test test, String name) throws ServiceException, SQLException {
        Connection connection =  null;
//        try {
//            connection= ConnectorDB.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            TestDAOImpl.getInstance().updateNameTestById(test,name);
//            connection.commit();
//        } catch (SQLException |PropertyVetoException | DAOException e) {
//            connection.rollback();
//            throw new ServiceException(e.getMessage());
//        } finally {
//            connection.close();
//        }

    }
    public void updateTest(Test test, List<Question> questionList,List<Answer> answerList) throws SQLException, ServiceException {

            testDAOImpl.update(test);
            Iterator<Question> itQuestion=questionList.iterator();
//
//            while (itQuestion.hasNext()){
//                Question question =itQuestion.next();
//                QuestionDAOImpl.getInstance(connection).update(question);
//                Iterator<Answer> itAnswer=answerList.iterator();
//
//                while (itAnswer.hasNext()){
//                    Answer answer=itAnswer.next();
//                    if(question.getQuestionId()==answer.getQuestionId()){
//                        AnswerDAOImpl.getInstance(connection).update(answer);
//                    }
//                }
//            }


    }
    public List<Test> findSubjectTestByPage(int subjectId,int recordsPerPage, int numberPage)  {
        List<Test> list= testDAOImpl.findSubjectTestByPage(subjectId,recordsPerPage, numberPage);
        return list;
    }

    public long countRecords(int subjectId){
        return testDAOImpl.countRecords(subjectId);
    }
}
