<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="database.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Movie List</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/movielist.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
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
        
         <div class="rowww">
		<h2 class="cursive-font">Movie List</h2>
        </div>
		<div id="Movies">
			<div class="row">

				<p>
					Order By: <a
						href="MovieList?${query}limit=${limit}&page=${page}&order=titleasc"
						class="seek">Title </a> <i class="ion-ios-arrow-thin-up iconsmall"></i>
					<a
						href="MovieList?${query}limit=${limit}&page=${page}&order=titledesc"
						class="seek">Title </a><i
						class=" ion-ios-arrow-thin-down iconsmall"></i> <a
						href="MovieList?${query}limit=${limit}&page=${page}&order=yearasc"
						class="seek">Year </a> <i class="ion-ios-arrow-thin-up iconsmall"></i>
					<a
						href="MovieList?${query}limit=${limit}&page=${page}&order=yeardesc"
						class="seek">Year </a><i
						class=" ion-ios-arrow-thin-down iconsmall"></i>
				</p>
			</div>
           </div>
         <div class="rowww">
			<table class="movieslist">
                  
				<thead class="message">
					<tr>
						<th>Poster</th>
						<th>Id & Title</th>
						<th>Year</th>
						<th>Director</th>
						<th>Genres</th>
						<th>Stars</th>
						<th>Trailer</th>
						<th>Sale</th>
					</tr>
				</thead>
              
            
				<tbody>
					<c:forEach items="${movies}" var="movie">
						<tr>
							<td><img class="space" src="${movie.banner_url}" width="150"
								height="200" alt="No Image" /></td>
							<td>
								<p>${movie.id}</p> <a href="SingleMovie?movieid=${movie.id}">${movie.title}</a>
							</td>
							<td>
								<p>${movie.year}</p>
							</td>
							<td>
								<p>${movie.director}</p>
							</td>
							<td><c:forEach items="${movie.genres}" var="genre">
									<p>${genre.name}</p>
								</c:forEach></td>
							<td><c:forEach items="${movie.stars}" var="star">
									<a href="SingleStar?starid=${star.id}">${star.first_name}
										${star.last_name},</a>
								</c:forEach></td>
							<td>
								<p>
									<a href="${movie.trailer_url}">Watch Trailer</a>
								</p>
							</td>
							<td>
								<form name="addMovieInCart"
									action="ShopCart?request=add_item&qty=1&movieid=${movie.id}"
									method="POST">
									<button type="submit" class="add_button">Add to Cart</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
           </div>
			<div class="row">
				<table>
					<tr>

						<td><c:if test="${page ne 0}">
								<a
									href="MovieList?${query}limit=${limit}&page=${page-1}&order=${order}"><button>Prev</button></a>
							</c:if></td>
						<td>
							<p>
								<a href="MovieList?${query}limit=5&page=0&order=${order}">5</a>
								<a href="MovieList?${query}limit=10&page=0&order=${order}">10</a>
								<a href="MovieList?${query}limit=25&page=0&order=${order}">25</a>
								<a href="MovieList?${query}limit=50&page=0&order=${order}">50</a>
								<a href="MovieList?${query}limit=100&page=0&order=${order}">100</a>
							</p>
						</td>
						<td><c:if test="${page lt max}">
								<a
									href="MovieList?${query}limit=${limit}&page=${page+1}&order=${order}"><button>Next</button></a>
							</c:if></td>
					</tr>
				</table>
			</div>
		


	
	</header>
	<footer>
	<div class="copyright">© FabFlix by Group 78</div>
	</footer>
</body>
</html>