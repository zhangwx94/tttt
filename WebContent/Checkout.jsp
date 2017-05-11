<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="database.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/checkout.css">
<title>FabFlix Checkout</title>
</head>
<body>

	<%
		if ((Customer) request.getSession().getAttribute("valid_customer") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>

	<header>
        <nav>
	<div class="row">
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
        

		<div class="row">
			<div class="message">
				<h2>FabFlix Checkout</h2>
			</div>
			<form name="Checkout" action="Checkout" method="POST">
				<p>
					Credit Card Number<br>
				</p>
				<input type="text" name="cc_id" required="required"
					placeholder="credit card number" />
				<p>
					Name on Card<br>
				</p>
				<input type="text" name="first_name" required="required"
					placeholder="first name" /> <input type="text" name="last_name"
					value="" required="required" placeholder="last name" />

				<p>
					Expiration Date (YYYY-MM-DD)<br>
				</p>
				<input type="text" name="expiration" required="required"
					placeholder="expiration date" /> <br> <input type="submit"
					name="checkout_submit" value="Check Out" class="checkout_button" />
				<h4>${invalid_checkout}</h4>
			</form>
		</div>
	</header>

	<footer>
		<div class="copyright">© FabFlix by Group 78</div>
	</footer>

</body>
</html>