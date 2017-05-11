<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>FabFlix Main</title>
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
				<li><a href="Main"><i class="fa fa-film" aria-hidden="true"></i> FABFLIX</a></li>
			</ul>
			<ul class="right-nav">
				<li><a href="Search.jsp"><i class="fa fa-search" aria-hidden="true"></i> Advanced Search</a></li>
				<li><a href="ShopCart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping Cart</a></li>
				<li><a href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Log Out</a></li>
			</ul>
		</ul>
	</div>
	</nav> <section class="browse">

	<div class="row genre">
		<div class="colu colu40 hint cursive-font">
			<h2>Browse by Genre</h2>
		</div>

		<div class="colu colu30 search">
			<form name="browse_genre" action="Main" method="POST">
				<select name="browse_genre_select">
					<c:forEach var="genre" items="${genres}">
						<option value="${genre}">${genre}</option>
					</c:forEach>
				</select> <input type="submit" name="browse_genre_submit" value="Browse"
					class="button_submit" />
			</form>
		</div>
		<div class="colu colu30"></div>
	</div>

	<div class="row">
		<div class="colu colu40 hint cursive-font">
			<h2>Browse by Title</h2>
		</div>

		<div class="colu colu30 search">
			<form name="browse_title" action="Main" method="POST">
				<select name="browse_title_select">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
					<option value="E">E</option>
					<option value="F">F</option>
					<option value="G">G</option>
					<option value="H">H</option>
					<option value="I">I</option>
					<option value="J">J</option>
					<option value="K">K</option>
					<option value="L">L</option>
					<option value="M">M</option>
					<option value="N">N</option>
					<option value="O">O</option>
					<option value="P">P</option>
					<option value="Q">Q</option>
					<option value="R">R</option>
					<option value="S">S</option>
					<option value="T">T</option>
					<option value="U">U</option>
					<option value="V">V</option>
					<option value="W">W</option>
					<option value="X">X</option>
					<option value="Y">Y</option>
					<option value="Z">Z</option>
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
				</select> <input type="submit" name="browse_title_submit" value="Browse"
					class="button_submit" />
			</form>
		</div>
	</div>
	</section> </header>


	<footer>
	<div class="copyright">© FabFlix by Group 78</div>
	</footer>
</body>
</html>