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
	<link rel="stylesheet" type="text/css" href="css/dashboard.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<title>FabFlix Dashboard</title>
</head><body>
	<header> 
	<nav>
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
		<h3>${update}</h3>
		<center>
			<table>
				<tr>
					<td>
						<form action="AddStar" method="GET">
							<fieldset class="field">
								<legend>Add Star</legend>
                                <div class="content">
                                    <div class="row">
                                        <div class="col1">
                                        <h4>Name</h4>
                                        </div>    
                                        <div class="col">
                                        <input type="text" name="name" />
                                        </div>   
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                        <h4>DOB</h4>
                                        </div>    
                                        <div class="col">
                                        <input type="text" name="dob" />
                                        </div> 
                                     </div>   
                                    <div class="row">
                                        <div class="col1">
                                        <h4>Photo_url</h4>
                                        </div>    
                                        <div class="col">
                                        <input type="text" name="photo_url" />
                                        </div> 
                                     </div> 
                                    <input type="submit" value="Add" class="submit" />
                                </div>
							</fieldset>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action="ProvideMetadata" method="GET">
							<fieldset class="field">
								<legend>Provide Metadata</legend>
                                <div class="content">
								<input type="submit" value="Provide Metadata" class="submit" />
                                </div>
							</fieldset>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action="AddMovie" method="GET">
							<fieldset class="filed">
								<legend>Add Movie</legend>
                                <div class="content">
                                    <div class="row">
                                        <div class="col1">
                                             <h4>Title</h4>
                                            </div>    
                                            <div class="col">
                                            <input type="text" name="title" />
                                            </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                            <h4>Year</h4>
                                            </div>    
                                            <div class="col">
                                            <input type="text" name="year" />
                                            </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                            <h4>Director</h4>
                                            </div>    
                                            <div class="col">
                                            <input type="text" name="director" />
                                            </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                            <h4>Banner_url</h4> 
                                        </div>
                                            <div class="col">
                                            <input type="text" name="banner_url" />
                                            </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                             <h4>Trailer_url</h4> 
                                            </div>    
                                            <div class="col">
                                            <input type="text" name="trailer_url" />
                                            </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                             <h4>First Name</h4> 
                                            </div>    
                                            <div class="col">
                                            <input type="text" name="fname">
                                            </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col1">
                                              <h4>Last Name</h4> 
                                            </div>    
                                            <div class="col">
                                           <input type="text" name="lname">
                                            </div> 
                                    </div>
                                     <div class="row">
                                        <div class="col1">
                                         <h4>DOB</h4> 
                                        </div>    
                                        <div class="col">
                                        <input type="text" name="dob" />
                                        </div> 
                                     </div>   
                                    <div class="row">
                                        <div class="col1">
                                            <h4>Photo_url</h4> 
                                        </div>    
                                        <div class="col">
                                        <input type="text" name="photo_url" />
                                        </div> 
                                     </div> 
                                    <div class="row">
                                        <div class="col1">
                                             <h4>Genre</h4> 
                                            </div>    
                                            <div class="col">
                                         <input type="text" name="genre">
                                            </div> 
                                    </div> 
                                    <input type="submit"value="Add" class="submit" />
                                </div>
							</fieldset>
						</form>
					</td>
				</tr>
			</table>
		</center>
	</div>

	</header>
	<footer>
	<div class="copyright">© FabFlix by Group 78</div>
	</footer>
</body>
</html>