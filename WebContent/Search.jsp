<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>	
<link rel="stylesheet" type="text/css" href="css/Search.css">
<link rel="stylesheet" type="text/css" href="css/general.css">
	
<title>FabFlix Searching</title>
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
		<div class="tablebody">
		<h2 class="cursive-font"><i class="fa fa-search" aria-hidden="true"></i> Search Options</h2>
		<form name="searchform" action="MovieList" method="GET">
				Title<br>
				<input type="text" name="title" placeholder="title" class="box"/>
				<div class="space"></div>
				Year<br>
				<input type="text" name="year" placeholder="year" class="box"/>
				<div class="space"></div>
				Director<br>
				<input type="text" name="director" placeholder="director" class="box"/>
				<div class="space"></div>
				Star's Name<br>
				<input	type="text" name="first_name" placeholder="first name" class="box"/>
				<div class="space"></div>
				<input	type="text" name="last_name" placeholder="last name" class="box"/>
				<div class="space"></div>
				<input type="hidden" name="is_search" value="true" class="box"/>
				<div class="space"></div>
				<input type="submit" name="searchsubmit" value="Search" class="button"/>
			</form>
		</div>
	</div>
</body>

<div class="space"></div>

<footer>
	<div class="copyright">© FabFlix by Group 78</div>
</footer>

</html>