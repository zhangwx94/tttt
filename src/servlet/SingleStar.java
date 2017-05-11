package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.*;
import sql.Query;

@WebServlet("/SingleStar")
public class SingleStar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = Query.makeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String starid = request.getParameter("starid");
		Star star = null;
		try {
			star = Query.searchStar(starid, conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("star", star);
		request.setAttribute("movies", star.getMovies());
		request.getRequestDispatcher("SingleStar.jsp").forward(request, response);
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
