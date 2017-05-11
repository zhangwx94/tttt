package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.Query;
import database.Movie;

@WebServlet("/MovieList")
public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
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
			String first_name = "";
			String last_name = "";
			String genre = "";
			Boolean is_search = false;
			int page = 0;
			int limit = 5;
			String order = "titleasc";
			
			if (request.getParameter("limit") != null)
			{
				limit = Integer.parseInt((String) request.getParameter("limit"));
			}
			
			if (request.getParameter("page") != null)
			{
				page = Integer.parseInt((String) request.getParameter("page"));
			}
			
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
			
			if (request.getParameter("first_name") != null)
			{
				first_name = request.getParameter("first_name");
			}
			
			if (request.getParameter("last_name") != null)
			{
				last_name = request.getParameter("last_name");
			}
			
			if (request.getParameter("order") != null)
			{
				order = request.getParameter("order");
			}
			
			
			if (request.getParameter("genre") != null) 
			{
				genre = request.getParameter("genre");
			}
			
			if( request.getParameter("is_search") != null){
				is_search = true;
			}
			
			ArrayList<Movie> tempMovies;
			if(is_search){
				tempMovies = Query.searchMovies("", title, year, director, first_name, last_name, conn);
			}
			else{
				tempMovies = Query.browseMovies(title, genre, conn);
			}
			

			if (order != null && !order.isEmpty())
			{
				if (order.equals("titleasc"))
				{
					Collections.sort(tempMovies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							return (o1.getTitle().compareTo(o2.getTitle()));
						}
					});
				}
				else if (order.equals("titledesc"))
				{
					Collections.sort(tempMovies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							return (o1.getTitle().compareTo(o2.getTitle()));
						}
					});
					Collections.reverse(tempMovies);
				}
				else if (order.equals("yearasc"))
				{
					Collections.sort(tempMovies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							if (o1.getYear() == o2.getYear()) {
								return 0;
							}
							return o1.getYear() < o2.getYear() ? -1 : 1;
						}
					});
				}
				else
				{
					Collections.sort(tempMovies, new Comparator<Movie>() {
						
						public int compare(Movie o1, Movie o2) 
						{
							if (o1.getYear() == o2.getYear()) {
								return 0;
							}
							return o1.getYear() < o2.getYear() ? 1 : -1;
						}
					});
				}
			}
			ArrayList<Movie> movies = new ArrayList<Movie>();
			int offset = (page * limit);
			
			for (int i = offset; i < (offset + limit); i++)
			{
				if (i <= tempMovies.size() - 1) 
				{
					movies.add(tempMovies.get(i));
				}
			}
			
			ArrayList<String> parArray = new ArrayList<String>();
			parArray.add("title");
			parArray.add("year");
			parArray.add("director");
			parArray.add("first_name");
			parArray.add("last_name");
			parArray.add("genre");
			parArray.add("is_search");
			
			ArrayList<String> queryArray = new ArrayList<String>();
			
			for (String temp: request.getQueryString().split("&"))
			{
				for (String param : parArray)
				{
					if (temp.startsWith(param))
					{
						queryArray.add(temp);
					}
				}
		    }
			
			String query = "";
			
			for (String param : queryArray)
			{
				query += param + "&";
			}
			
			int max = tempMovies.size()/limit;
			
			request.setAttribute("query", query);
			request.setAttribute("movies", movies);
			request.setAttribute("limit", limit);
			request.setAttribute("page", page);
			request.setAttribute("order", order);
			request.setAttribute("max", max);
			
			request.getRequestDispatcher("MovieList.jsp").forward(request, response);
			
			if (conn != null && !conn.isClosed())
			{
				conn.close();
			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
