package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.Query;

@WebServlet("/ProvideMetadata")
public class ProvideMetadata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = Query.makeConnection();
			}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML><HEAD><TITLE>Metadata</TITLE></HEAD>");
        out.println("<BODY><H1>Metadata</H1>");
        ResultSet tables = conn.getMetaData().getTables(null,null,"%",null);
        while(tables.next()){
        	out.print("<TABLE border>");
        	out.print("<tr>" + "<td>" + "Table" +"</td>"+ "<td>"+ tables.getString(3)+"</td>" +"</tr>");
        	Statement stmt = conn.createStatement();
    		ResultSet res = stmt.executeQuery("select * from " + tables.getString(3));
    		ResultSetMetaData metadata = res.getMetaData();
    		for (int i = 1; i <= metadata.getColumnCount(); i++) {
    			out.println("<tr>" + "<td>" + metadata.getColumnName(i) + "</td>" + "<td>" + metadata.getColumnTypeName(i)+"</td>" +"</tr>");
    		}
    		out.println("</TABLE><br>");
    		
        }
        
        out.println("<a href=\"Dashboard.jsp\">Back</a>");
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}



/*public static void ProvideMetadata(Connection conn) throws Exception  {
	

	while(tables.next()) {
		System.out.println("Table: " + tables.getString(3));
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("select * from " + tables.getString(3));
		ResultSetMetaData metadata = (ResultSetMetaData) res.getMetaData();
		for (int i = 1; i <= metadata.getColumnCount(); i++) {
			System.out.println(metadata.getColumnName(i) + ": " + metadata.getColumnTypeName(i));
		}
		System.out.println("");
	}
	tables.close();
}*/