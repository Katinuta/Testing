package by.htp.teploukhava.testing.exception;

import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.QuestionDAOImpl;
import by.htp.teploukhava.testing.entities.Answer;
import by.htp.teploukhava.testing.entities.Question;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 02.10.16.
 */
@Service
@Transactional
public class QuestionService implements AbstractService<Question> {
    private QuestionDAOImpl questionDAOImpl;

    public QuestionService(){}

    @Autowired
    public QuestionService(QuestionDAOImpl questionDAOImpl){
        this.questionDAOImpl=questionDAOImpl;
    }

    @Override
    public boolean create(Question entity)  {
        boolean flag=false ;

        return flag;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public void delete(int id)  {
        questionDAOImpl.delete(id);
    }

    @Override
    public Question update(Question entity) {
        return null;
    }

    @Override
    public Question find(int id) throws ServiceException {
        return null;
    }

    public List<Question> findTestQuestions(int testId) throws  ServiceException {
        List<Question> questionList= questionDAOImpl.findTestQuestions(testId);
        return questionList;
    }

    public Question findQuestionByContentAndTest(String content, int testId) throws  ServiceException {
        Question question=null;

        return question;
    }

    public void updateContextQuestion(Question question, String content) throws ServiceException {


    }

    public void createQuestion(Question question, List<Answer> answerList) throws  ServiceException {


    }
}
