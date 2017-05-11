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
<link rel="stylesheet" type="text/css" href="css/Single.css">
<link rel="stylesheet" type="text/css" href="css/general.css">
<title>FabFlix Single Movie</title>
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
		<h2 class="cursive-font">Movie</h2>
		<div class="tablebody">
			<table>
				<tr>
					<td class="tdimg"><img src="${movie.banner_url}" width="150"
						height="200" alt="No Image" /></td>
					<td>
						<h4>Id: ${movie.id}</h4>
						<h4>Title: ${movie.title}</h4>
						<h4>Year: ${movie.year}</h4>
						<h4>Director: ${movie.director}</h4>
						<p>
							Stars:
							<c:forEach items="${stars}" var="star">
								<a href="SingleStar?starid=${star.id}">${star.first_name}
									${star.last_name}</a>, 
					</c:forEach>
						</p>
						<p>
							Genres:
							<c:forEach items="${genres}" var="genre">
								<a href="MovieList?genre=${genre.name}"> ${genre.name}</a>,
					</c:forEach>
						</p>
						<p>
							<a href="${movie.trailer_url}">Watch Trailer</a>
						</p>
					</td>
				</tr>
			</table>

			<form name="addMovieInCart"
				action="ShopCart?request=add_item&qty=1&movieid=${movie.id}"
				method="POST">
				<button type="submit" class="button">Add to Cart</button>
			</form>


		</div>
	</div>
	<br>
	<br>
</body>

<footer>
<div class="copyright">© FabFlix by Group 78</div>
</footer>
</html>