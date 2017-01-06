//import by.htp.teploukhava.testing.entities.User;
//import by.htp.teploukhava.testing.command.LoginCommand;
//import by.htp.teploukhava.testing.command.factory.ActionCommand;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionContext;
//import java.util.Enumeration;
//
//import static junit.framework.TestCase.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by Admin on 26.09.16.
// */
//public class LoginCommandTest {
//    User user;
//    @Mock
//    HttpServletRequest request;
//
//    @Before
//    public void setUp() throws Exception {
//        user = new User();
//        user.setLogin("cat");
//        user.setPassword("5e56e9616c42b559f659e942d6d25aa9");
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        user = null;
//    }
//
//    @Test
//    public void executeCorrectLogin() throws Exception {
//        ActionCommand command = new LoginCommand();
//        request = mock(HttpServletRequest.class);
//        when(request.getParameter("login")).thenReturn("cat");
//        when(request.getParameter("password")).thenReturn("111");
//        when(request.getSession()).thenReturn(new HttpSession() {
//            @Override
//            public long getCreationTime() {
//                return 0;
//            }
//
//            @Override
//            public String getId() {
//                return null;
//            }
//
//            @Override
//            public long getLastAccessedTime() {
//                return 0;
//            }
//
//            @Override
//            public ServletContext getServletContext() {
//                return null;
//            }
//
//            @Override
//            public void setMaxInactiveInterval(int i) {
//
//            }
//
//            @Override
//            public int getMaxInactiveInterval() {
//                return 0;
//            }
//
//            @Override
//            public HttpSessionContext getSessionContext() {
//                return null;
//            }
//
//            @Override
//            public Object getAttribute(String s) {
//                return null;
//            }
//
//            @Override
//            public Object getValue(String s) {
//                return null;
//            }
//
//            @Override
//            public Enumeration<String> getAttributeNames() {
//                return null;
//            }
//
//            @Override
//            public String[] getValueNames() {
//                return new String[0];
//            }
//
//            @Override
//            public void setAttribute(String s, Object o) {
//
//            }
//
//            @Override
//            public void putValue(String s, Object o) {
//
//            }
//
//            @Override
//            public void removeAttribute(String s) {
//
//            }
//
//            @Override
//            public void removeValue(String s) {
//
//            }
//
//            @Override
//            public void invalidate() {
//
//            }
//
//            @Override
//            public boolean isNew() {
//                return false;
//            }
//        });
//        String actualPage = command.execute(request);
//        assertEquals("jsp/main.jsp", actualPage);
//    }
//
//}
//
