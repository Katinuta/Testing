package by.htp.teploukhava.testing.controler;

import by.htp.teploukhava.testing.entities.User;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Admin on 14.11.16.
 */
@org.springframework.stereotype.Controller
@SessionAttributes ("user")
public class LoginController {
//
//    @RequestMapping(value="/",method = RequestMethod.GET)
//    public ModelAndView main(){
//        return new ModelAndView("login", "user",new User());
//    }
//    @ModelAttribute("user")
//    public User loadEmptyModelBean(){
//        return new User();
//    }

    private UserService userService;

    @Autowired
    public LoginController(UserService userService){

        this.userService=userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
      //model.addAttribute("user",new User());
//        System.out.println("LOGIN FROM  REQUEST MAPPING");
        return new ModelAndView("login","user",new User());
    }

    @RequestMapping(value="/check-user",method = RequestMethod.POST)
    public String checkUser(@ModelAttribute("user") User user,Model model){
        if(userService.checkUser(user.getLogin(),user.getPassword())){
            user=userService.findUserByLogin(user.getLogin());
            model.addAttribute("user",user);
            return "main";
        }else{
            return "login";
        }
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

}
