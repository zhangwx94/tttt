package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sql.Query;
import database.*;

@WebServlet("/Login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Connection conn = null;
		try{
			if(conn == null || conn.isClosed()){
				conn = Query.makeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Customer valid_customer = null;
		try {
			valid_customer = Query.verifyCustomer(email, password, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(valid_customer != null){
			HttpSession session = request.getSession();
			session.setAttribute("valid_customer", valid_customer);
			session.setAttribute("connecton", conn);
			session.setAttribute("cart", new Cart());
			request.setAttribute("invalid_customer", "");
			response.sendRedirect("Main");
		}
		else{
			request.setAttribute("invalid_customer", "Invalid email or password!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		try {
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
