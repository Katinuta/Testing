package by.htp.teploukhava.testing.controller;

import by.htp.teploukhava.testing.entities.User;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Admin on 14.11.16.
 */
@org.springframework.stereotype.Controller
@SessionAttributes ("user")
public class UserController {

    private UserService userService;

    public UserController(){}

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @RequestMapping(value =("/"), method = RequestMethod.GET)
    public String main(@RequestParam(value = "error",required = false) String error) {
        return "login";
    }

    @RequestMapping(value="/registration",method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @RequestMapping(value="/add-user", method=RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model, SessionStatus sessionStatus){
        user.setAccess(false);
        userService.create(user);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @RequestMapping(value = "/accessDenied",method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user){
        ModelAndView model=new ModelAndView();
        if(user!=null){
            model.addObject("errorMsg",user.getName()+"У вас нет доступа  к этой странице");
        }else{
            model.addObject("errorMsg","У вас нет доступа  к этой странице");
        }
        model.setViewName("accessDenied");
        return model;
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              ModelAndView model) {

    //    ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
     //   model.setViewName("login");
        return "login";

    }
}
