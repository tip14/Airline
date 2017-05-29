<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="add">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${cssPath}" type="text/css">
<title>Login | Airline Project</title>
</head>

<body>

	<%@include file="navigation.jsp"%>
	
	<p class="reg-msg">Please, enter your credentials</p>
	<form action="login" method="POST">
		<div>
			<label for="email">E-mail:</label> 
			<input type="text" id="email" name="email">
		</div>
		<div>
			<label for="pass">Password:</label> 
			<input type="password" id="pass" name="pass">
			<p class="field-err-msg">${notExistsError}</p>
		</div>		
		<input type="submit" value="LogIn">
	</form>
	<p class=""><a href="registration">Registration</a></p>

</body>
</html>
