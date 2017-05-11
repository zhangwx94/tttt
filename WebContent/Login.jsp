<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FabFlix Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/7aa736d580.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	<%
		if (request.getSession(false) != null && request.getSession().getAttribute("valid_customer") != null) {
			response.sendRedirect("Main");
		}
	%>
	<header>
	<div class="login">
		<h1><i class="fa fa-film" aria-hidden="true"></i> FABFLIX</h1>
		<div class="message">Login</div>
		<form id="loginform" action="Login" method="Post">
			<input name="email" placeholder="Email" required="required"
				type="email" /> <br> <input name="password"
				placeholder="Password" required="required" type="password" /> <br>
			<select name = "user"><option value = "1">Customer</option><option value="2">Employee</option></select><br>
			
		<div class="g-recaptcha" data-sitekey="6Lftix8UAAAAAPqxXLLzTr0MTbY4W08DFlKZXGC2"></div>
			<h4>${invalid_customer}</h4>
            <input type="submit" value="Login" id="login_button" />
		</form>

	</div>
	</header>

	<footer>
	<div class="copyright">© FabFlix by Group 78</div>
	</footer>

</body>
</html>