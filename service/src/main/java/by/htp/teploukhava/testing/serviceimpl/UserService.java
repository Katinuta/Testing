package by.htp.teploukhava.testing.serviceimpl;


import by.htp.teploukhava.testing.AbstractService;
import by.htp.teploukhava.testing.dao.impl.UserDAOImpl;
import by.htp.teploukhava.testing.entities.Subject;
import by.htp.teploukhava.testing.entities.SubjectDTO;
import by.htp.teploukhava.testing.entities.User;
import by.htp.teploukhava.testing.util.LoginLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by Admin on 01.10.16.
 */
@Service
@Transactional
public class UserService implements AbstractService<User> {


    private UserDAOImpl userDAOImpl;

    @Autowired
    public UserService(UserDAOImpl userDAOImpl){
        this.userDAOImpl=userDAOImpl;
    }

    @Override
    public boolean create(User entity)  {
        boolean flag=false;
        userDAOImpl.create(entity);
        return flag;
    }

    @Override
    public List<User> findAll()  {
        return null;
    }

    @Override
    public void delete(int id)  {

    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User find(int id) {
        User user=userDAOImpl.find(id);

        return user;
    }

    public List<Subject> findUserSubject(User user)  {
        List<Subject> list= userDAOImpl.findUserSubject(user.getUserId());
        return list;
    }

    public User findUserByLogin(String login)  {

        User user=userDAOImpl.findUserByLogin(login);
        return user;

    }

    public boolean checkUser(String login, String password)  {

        User user=userDAOImpl.findUserByLogin(login);
        if(LoginLogic.checkLogin(user,login,password)){
            return true;
        }
        return false;

    }
    public List<SubjectDTO> findUserSubjectPage(int userId, int numberPage, int recordsPerPage)  {

        List<SubjectDTO> list= userDAOImpl.findUserSubjectPage(userId,numberPage,recordsPerPage);
        return list;

    }

    public long countUserSubject(int userId){
        return userDAOImpl.countUserSubject(userId);
    }
}
