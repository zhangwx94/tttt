package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.Query;

@WebServlet("/Main")
public class Main extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Connection conn = null;
		try{
			if(conn == null || conn.isClosed()){
				conn = Query.makeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> genre_name = null;
		try {
			genre_name = Query.getGenreName(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("genres", genre_name);
		request.getRequestDispatcher("Main.jsp").forward(request, response);
		try {
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = "MovieList?";
		if (request.getParameter("browse_genre_select") != null) 
		{
		    path += "genre=" + request.getParameter("browse_genre_select");
		}
		else if (request.getParameter("browse_title_select") != null) 
		{
			path += "title=" + request.getParameter("browse_title_select");
		}
	    response.sendRedirect(path);
	}
}
