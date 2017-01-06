package by.htp.teploukhava.testing.security;

import by.htp.teploukhava.testing.dao.impl.UserDAOImpl;
import by.htp.teploukhava.testing.entities.User;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 20.11.16.
 */
@Service
public class AuthenticationUserDetails implements UserDetailsService {
    @Autowired
    private UserService userService;

    private static final Logger logger= LogManager.getLogger(AuthenticationUserDetails.class);

    public AuthenticationUserDetails(){}

    public AuthenticationUserDetails(UserDAOImpl userDAOImpl){
        this.userService=userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userService.findUserByLogin(login);
        logger.info(user.toString());
        System.out.println(user.toString());
       List<GrantedAuthority> authority=new ArrayList<>();
        System.out.println(user.isAccess());
        if(user.isAccess()){
            authority.add(new SimpleGrantedAuthority("ROLE_ADMIN")) ;
        }else{
            authority.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
         UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true,
                 true, true, true,  authority);

        System.out.println(userDetails.getUsername()+" "+ userDetails.getPassword());
        logger.info(user.getLogin()+ "   " +user.getPassword());
        System.out.println(user.getLogin()+ "   " +user.getPassword());
        return userDetails;
    }
}

