package com.bridgelabz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	/*
	 * / Regex pattern for user name
	 */
	String user = request.getParameter("user");
	String namePattern = "^[A-Z]{1}[a-z]{3,}$";
	Pattern pat = Pattern.compile(namePattern);
	Matcher match = pat.matcher(user);
	/*
	 * / Regex pattern for password
	 */
	String pwd = request.getParameter("pwd");
	String pass = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&-+=()]).{8,}$";
	Pattern p2 = Pattern.compile(pass);
	Matcher m2 = p2.matcher(pwd);
	String userId = getServletConfig().getInitParameter("user");
	String password = getServletConfig().getInitParameter("password");
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
