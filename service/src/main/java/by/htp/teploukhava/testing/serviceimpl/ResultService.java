package by.htp.teploukhava.testing.serviceimpl;


import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.ResultDAOImpl;
import by.htp.teploukhava.testing.entities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 02.10.16.
 */
@Service
@Transactional
public class ResultService implements AbstractService<Result> {

    private ResultDAOImpl resultDAOImpl;

    public ResultService(){}
    @Autowired
    public ResultService(ResultDAOImpl resultDAOImpl){
        this.resultDAOImpl=resultDAOImpl;
    }

    @Override
    public boolean create(Result entity)  {
        boolean flag=false;
        resultDAOImpl.create(entity);
        flag=true;
        return flag;
    }

    @Override
    public List<Result> findAll()  {
        return null;
    }

    @Override
    public void delete(int id)  {

    }

    @Override
    public Result update(Result entity) {
        return null;
    }

    @Override
    public Result find(int id)  {
        return null;
    }

    public Result findResultByTestUser(int testId, int userId)  {
        Result result= resultDAOImpl.findResultByTestUser(testId, userId);
        return result;

    }
}
