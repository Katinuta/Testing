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

    public List<Test> findSubjectTestByPage(int subjectId,int recordsPerPage, int numberPage)  {
        List<Test> list= testDAOImpl.findSubjectTestByPage(subjectId,recordsPerPage, numberPage);
        return list;
    }

    public long countRecords(int subjectId){
        return testDAOImpl.countRecords(subjectId);
    }
}
