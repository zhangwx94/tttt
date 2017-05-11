<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="database.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<link rel="stylesheet" type="text/css" href="css/checksuc.css">
<title>FabFlix Checkout Successfully</title>
</head>
<body>


	<%
		if ((Customer) request.getSession().getAttribute("valid_customer") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	<header> <nav>
	<div class="row">
		<ul class="navall">
			<ul class="left-nav">
				<li><a href="Main"><i class="fa fa-film" aria-hidden="true"></i>
						FABFLIX</a></li>
			</ul>
			<ul class="right-nav">
				<li><a href="Search.jsp"><i class="fa fa-search"
						aria-hidden="true"></i> Advanced Search</a></li>
				<li><a href="ShopCart.jsp"><i class="fa fa-shopping-cart"
						aria-hidden="true"></i> Shopping Cart</a></li>
				<li><a href="Logout"><i class="fa fa-sign-out"
						aria-hidden="true"></i> Log Out</a></li>
			</ul>
		</ul>
	</div>
	</nav>


	<div class="row">
		<div class="message">

			<h1>Checkout Successfully</h1>
		</div>
	</div>

	</header>
	<footer>
	<div class="copyright">© FabFlix by Group 78</div>
	</footer>
</body>
</html>