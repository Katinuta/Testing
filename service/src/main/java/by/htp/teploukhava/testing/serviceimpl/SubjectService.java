package by.htp.teploukhava.testing.serviceimpl;


import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.SubjectDAOImpl;
import by.htp.teploukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.entities.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 01.10.16.
 */
@Service
@Transactional
public class SubjectService implements AbstractService<Subject> {

    private SubjectDAOImpl subjectDAOImpl;


    @Autowired
    public  SubjectService(SubjectDAOImpl subjectDAOImpl){
        this.subjectDAOImpl=subjectDAOImpl;
    }

    @Override
    public boolean create(Subject entity)  {
        boolean flag=false;
        subjectDAOImpl.create(entity);
        return flag;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> list= subjectDAOImpl.findAll();
        return list;
    }

    @Override
    public void delete(int id) {}

    @Override
    public Subject update(Subject entity) {
        return null;
    }

    @Override
    public Subject find(int id) {
        Subject subject=subjectDAOImpl.find(id);
        return subject;
    }
    public List<SubjectDTO> findByPage(int recordsPerPage, int numberPage)  {
        List<SubjectDTO> list= subjectDAOImpl.findByPage(recordsPerPage, numberPage);
        return list;
    }

    public long countRecords(){
        return subjectDAOImpl.countRecords();
    }
}
