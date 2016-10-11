package by.htp.teploukhava.testing.controler;

import by.htp.teploukhava.testing.command.factory.ActionCommand;
import by.htp.teploukhava.testing.command.factory.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		ActionFactory client = new ActionFactory();
		request.setCharacterEncoding("UTF-8");

		ActionCommand command = client.defineCommand(request);
		page = command.execute(request);
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher(page).forward(request, response);
	}

}
