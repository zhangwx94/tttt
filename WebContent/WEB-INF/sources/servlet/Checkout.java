package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sql.Query;
import database.*;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cc_id = request.getParameter("cc_id");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String expiration = request.getParameter("expiration");

		Connection conn = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = Query.makeConnection();
			}
			HttpSession session = request.getSession(false);
			String sql = "SELECT COUNT(*) FROM creditcards WHERE id=? AND first_name=? AND last_name=? AND expiration=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cc_id);
			pstmt.setString(2, first_name);
			pstmt.setString(3, last_name);
			pstmt.setDate(4, Date.valueOf(expiration));
			ResultSet res = pstmt.executeQuery();
			res.next();
			int count = res.getInt(1);
			if (count == 1) {
				Customer customer = (Customer) session.getAttribute("valid_customer");
				Cart cart = (Cart) session.getAttribute("cart");
				Query.insertSale(cart, customer, conn);
				cart.removeAllItems();
				System.out.println("12");
				session.setAttribute("cart", cart);
				//request.setAttribute("checkout", true);
				request.setAttribute("invalid_checkout", "");
				request.getRequestDispatcher("CheckoutSuccess.jsp").forward(request, response);
			} 
			else {
				request.setAttribute("invalid_checkout", "Invalid credit card information!");
				request.getRequestDispatcher("Checkout.jsp").forward(request, response);
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
