package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sql.Query;
import database.*;

@WebServlet("/ShopCart")
public class ShopCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection conn = null;
			try{
				if(conn == null || conn.isClosed()){
					conn = Query.makeConnection();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession session = request.getSession(false);
			Cart cart = (Cart) session.getAttribute("cart");
			String shop = request.getParameter("request").toLowerCase();
			String movieId = request.getParameter("movieid");
			int qty = 1;
			if (request.getParameter("qty") != null){
				qty = Integer.parseInt(request.getParameter("qty"));
			}
			Movie movie = Query.searchMovies(movieId, "", "", "", "", "",  conn).get(0);
			if (shop.equals("add_item")){
				cart.addItemInCart(movie, qty);
			}
			else if (shop.equals("update_item_qty")){
				cart.updateItemQty(movie, qty);
			}
			else if (shop.equals("remove_item")){
				cart.removeItemInCart(movie);
			}
			else if (shop.equals("remove_all_items")){
				cart.removeAllItems();
			}
			session.setAttribute("cart", cart);
			response.sendRedirect("ShopCart.jsp");
			if (conn != null && !conn.isClosed()){
				conn.close();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
