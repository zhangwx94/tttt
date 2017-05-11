package servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.Query;

@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection conn = null;
			try{
				if(conn == null || conn.isClosed()){
					conn = Query.makeConnection();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String title = "";
			String year = "";
			String director = "";
			String banner_url = "";
			String trailer_url = "";
			String fname = "";
			String lname = "";
			String dob = "";
			String photo_url = "";
			String genre = "";
			
			if (request.getParameter("title") != null) 
			{
				title = request.getParameter("title");
			}
			
			if (request.getParameter("year") != null)
			{
				year = request.getParameter("year");
			}
			
			if (request.getParameter("director") != null)
			{
				director = request.getParameter("director");
			}
			
			if (request.getParameter("banner_url") != null)
			{
				banner_url = request.getParameter("banner_url");
			}
			
			if (request.getParameter("trailer_url") != null)
			{
				trailer_url = request.getParameter("trailer_url");
			}
			
			if (request.getParameter("fname") != null)
			{
				fname = request.getParameter("fname");
			}
			
			if (request.getParameter("lname") != null)
			{
				lname = request.getParameter("lname");
			}
			
			if (request.getParameter("dob") != null)
			{
				dob = request.getParameter("dob");
			}
			
			if( request.getParameter("photo_url") != null){
				photo_url = request.getParameter("photo_url");
			}
			
			if (request.getParameter("genre") != null) 
			{
				genre = request.getParameter("genre");
			}
			
			String[] dobs = dob.split("-");
			if(title == "" || year == "" || dobs.length < 3 || lname == "" || genre == ""){
				request.setAttribute("update", "Fail to add!");
			}
			else{
				CallableStatement cs = conn.prepareCall("{call add_movie(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, title);
				cs.setString(2, year);
				cs.setString(3, director);
				cs.setString(4, banner_url);
				cs.setString(5, trailer_url);
				cs.setString(6, fname);
				cs.setString(7, lname);
				cs.setString(8, dob);
				cs.setString(9, photo_url);
				cs.setString(10, genre);
				cs.registerOutParameter(11, Types.VARCHAR);
				cs.execute();
				String update = cs.getString(11);
				request.setAttribute("update", update);
			}
			
			request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
			if (conn != null && !conn.isClosed()){
				conn.close();
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
