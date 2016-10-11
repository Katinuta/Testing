package by.htp.teploukhava.testing.command;

import by.htp.telpoukhava.testing.entities.User;
import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.serviceimpl.ServiceException;
import by.htp.teploukhava.testing.serviceimpl.UserService;
import by.htp.teploukhava.testing.util.LoginLogic;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/** Class describe command of authentication user */

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final Logger logger= LogManager.getLogger(LoginCommand.class);
	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		User user;
//		ResourceBundle resource = ResourceBundle.getBundle("Resources/database");
//		String url = resource.getString("url") + "?" + "useUnicode=" + resource.getString("useUnicode") + "&"
//				+ "characterEncoding=" + resource.getString("characterEncoding");
//		System.out.println(url);
//		logger.info("!!!!!!!!!!!!!!!!!"+url);
//		String driver = resource.getString("driver");
//		System.out.println(driver);
//		logger.info("!!!!!!!!!!!!!!!!!"+driver);
//		String userdb = resource.getString("user");
//		logger.info("!!!!!!!!!!!!!!!!!"+userdb);
//		System.out.println(userdb);
//		String passworddb = resource.getString("password");
//		logger.info("!!!!!!!!!!!!!!!!!"+passworddb);
//		System.out.println(password);
//		 ComboPooledDataSource cpds;
//		cpds = new ComboPooledDataSource();
//		try {
//			cpds.setDriverClass(driver);
//			cpds.setJdbcUrl(url);
//			cpds.setUser(userdb);
//			cpds.setPassword(passworddb);
//			cpds.setMinPoolSize(5);
//			cpds.setAcquireIncrement(5);
//			cpds.setMaxPoolSize(15);
//			cpds.setMaxStatements(180);
//			//logger.info(cpds.getJdbcUrl());
//			Connection cn = cpds.getConnection();
//			logger.info("!!!!!!!"+cn.getMetaData().getURL() +"  connetion");
//            logger.info("!!!!!!!"+cn.getMetaData().getUserName() +"  connetion");
//			System.out.println(cn.getMetaData().getURL());
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//
		try {
			user = UserService.getInstance().findUserByLogin(login);
			if (LoginLogic.checkLogin(user, login, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				page = "jsp/main.jsp";
			} else {
				request.setAttribute("errorLoginMessage", "Incorrect login or password");
				page = "login.jsp";
			}

		} catch (SQLException | ServiceException e) {
			page="jsp/error.jsp";
		}
		return page;
	}

}
