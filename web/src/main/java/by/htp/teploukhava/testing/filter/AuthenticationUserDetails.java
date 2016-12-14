package by.htp.teploukhava.testing.filter;

import by.htp.teploukhava.testing.dao.impl.UserDAOImpl;
import by.htp.teploukhava.testing.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 20.11.16.
 */
@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private UserDAOImpl userDAOImpl;

    public MyUserDetails(UserDAOImpl userDAOImpl){
        this.userDAOImpl=userDAOImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userDAOImpl.findUserByLogin(login);
       List<GrantedAuthority> authority=null;
        if(user.isAccess()){
            authority.add(new SimpleGrantedAuthority("ROLE_ADMIN")) ;
        }else{
            authority.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
         UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),authority);
        return userDetails;
    }
}
