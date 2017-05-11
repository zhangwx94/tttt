<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="database.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<link rel="stylesheet" type="text/css" href="css/Shopcart.css">
<link rel="stylesheet" type="text/css" href="css/general.css">
<title>FabFlix Shop Cart</title>
</head>
<body>

	<%
		if ((Customer) request.getSession().getAttribute("valid_customer") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>

<nav>
 <div class="rowww">
  <ul class="navall">
   <ul class="left-nav">
    <li><a href="Main"><i class="fa fa-film" aria-hidden="true"></i> FABFLIX</a></li>
   </ul>
   <ul class="right-nav">
    <li><a href="Search.jsp"><i class="fa fa-search" aria-hidden="true"></i> Advanced Search</a></li>
    <li><a href="ShopCart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping Cart</a></li>
    <li><a href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Log Out</a></li>
   </ul>
  </ul>
 </div>
 </nav>
<br/>
	<div class="table">
		<div class="tablehead"></div>
		<h2 class="cursive-font">Shopping Cart</h2>
		<div class="tablebody">
			<table>
				<tr>
					<td><h3 class="tdMovie">
							<strong>Movie</strong>
						</h3></td>
					<td><h3>
							<strong>Quantity</strong>
						</h3></td>
				</tr>
				<c:forEach items="${cart.cartItems}" var="cartItem">
					<tr>
						<td>${cartItem.movie.title}</td>
						<td>
							<form method="POST" action="ShopCart">
								<input type="text" name="qty" value="${cartItem.qty}"
									class="tdQuantity" /> <input type="hidden" name="movieid"
									value="${cartItem.movie.id}" />
								<button name="request" value="update_item_qty" type="submit"
									class="button1" />
								Update Quantity
								</button>
								<button name="request" value="remove_item" type="submit"
									class="button1" />
								Remove
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="check">
				<div class="checkout">
					<a href="Checkout.jsp"><button class="button">Checkout</button></a>
					</td>
				</div>
				<form method="POST" action="ShopCart">
					<!--  <input type="hidden" name="movieid" value="${cartItem.getKey().id}"/>-->
					<button name="request" value="remove_all_items" type="submit"
						class="button">Clear Cart</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
</body>



<footer>
<div class="copyright">© FabFlix by Group 78</div>
</footer>
</html>