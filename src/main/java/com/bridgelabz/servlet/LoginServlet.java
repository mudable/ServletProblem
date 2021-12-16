package com.bridgelabz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/* used WebServlet annotation to pass the url description.
 * and WebInitParam annotation we can pass the parameter and value.
 */
@WebServlet(description = "Login Servlet Testing", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "Shilpa"), @WebInitParam(name = "password", value = "12345") })
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String userId = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		/* applied the conditaion wether the given,
		 *  user name is valid or not
		 */
		if (userId.equals(user) && password.equals(pwd)) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font colour=red> Either User name or Password is Wrong</font>");
			rd.include(request, response);
		}
	}
}
