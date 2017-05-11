package servlet;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.Query;

@WebServlet("/AddStar")
public class AddStar extends HttpServlet {
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
			
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String photo_url = request.getParameter("photo_url");
			int num = Query.AddStar(name, dob, photo_url, conn);
			if(num == 0){
				request.setAttribute("update", "Fail to add!");
			}
			else{
				request.setAttribute("update", "Added star successfully!");
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
