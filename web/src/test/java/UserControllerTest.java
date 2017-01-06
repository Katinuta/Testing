import by.htp.teploukhava.testing.controller.UserController;
import by.htp.teploukhava.testing.entities.User;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 26.09.16.
 */
public class UserControllerTest {
    User user;
    Model model;
    UserController userController;
    ModelAndView modelExpected;
    ModelAndView modelActual;
    String error ;
    String logout ;

    @Before
    public void setUp() throws Exception {
        user = new User("name", "surname", "login", "password", false);
        model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        userController = new UserController();
        modelExpected = new ModelAndView();
        modelActual = new ModelAndView();
    }

    @After
    public void tearDown() throws Exception {
        user=null;
        model=null;
        modelExpected=null;
        modelActual =null;
        userController=null;
    }

    @Test
    public void addUserTest() {
        UserService userService = mock(UserService.class);
        when(userService.create(user)).thenReturn(true);
        UserController userController = new UserController(userService);
        SessionStatus sessionStatus = new SessionStatus() {
            @Override
            public void setComplete() {

            }

            @Override
            public boolean isComplete() {
                return false;
            }
        };
        String actualPage = userController.addUser(user, model, sessionStatus);
        assertEquals("redirect:/", actualPage);
    }

    @Test
    public void mainTest() {
        error = new String();
        String actualPage = userController.main(error);
        assertEquals("login", actualPage);
    }

    @Test
    public void registrationTest() {
        String actualPage = userController.registration();
        assertEquals("registration", actualPage);
    }

    @Test
    public void loginTest() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        error = null;
        logout = null;
        String actualPage = userController.login(error, logout, model);
        assertEquals("login", actualPage);
    }

    @Test
    public void loginTestErrorNotNull() {
        modelExpected.addObject("error", "Invalid username and password!");
        error = new String();
        logout = null;
        userController.login(error, logout, modelActual);
        assertSame(modelExpected.getModel().get("error"), modelActual.getModel().get("error"));
    }

    @Test
    public void loginTestLogoutNotNull() {
        modelExpected.addObject("msg", "You've been logged out successfully.");
        error = null;
        logout = new String();
        userController.login(error, logout, modelActual);
        assertSame(modelExpected.getModel().get("msg"), modelActual.getModel().get("msg"));
    }

    @Test
    public void accessDeniedTest() {
        Principal user = null;
        modelExpected.setViewName("accessDenied");
        modelExpected.addObject("errorMsg", "У вас нет доступа  к этой странице");
        ModelAndView model = userController.accessDenied(user);
        assertSame(modelExpected.getViewName(), model.getViewName());
        assertSame(modelExpected.getModel().get("errorMsg"), model.getModel().get("errorMsg"));
    }
   @Ignore
    @Test
    public void accessDeniedUserNotNullTest() {
        Principal user = mock(Principal.class);
        modelExpected.setViewName("accessDenied");
        modelExpected.addObject("errorMsg", "name У вас нет доступа  к этой странице");
        when(user.getName()).thenReturn("name ");
        ModelAndView model = userController.accessDenied(user);
        assertSame(modelExpected.getViewName(), model.getViewName());
        assertSame(modelExpected.getModel().get("errorMsg"), model.getModel().get("errorMsg"));
    }

}

