<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="add">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${cssPath}" type="text/css">
<title>Registration | Airline Project</title>
</head>

<body>

	<%@include file="navigation.jsp"%>
	
	<p>Please, fill fields below for registration</p>
	<form action="registration" method="POST">
		<div>
			<label for="email">E-mail:</label> 
			<input type="text" id="email" name="email">
			<p class="">${emailFillingError}</p>
		</div>
		<div>
			<label for="pass">Password:</label> 
			<input type="password" id="pass" name="pass">
			<p class="">${passFillingError}</p>
		</div>
		<div>
			<label for="pass-repeat">Repeat:</label>
			<input type="password" id="pass-repeat" name="pass-repeat">
			<p class="">${passRepeatFillingError}</p>
		</div>
		<div>
			<label for="role">Role:</label>
			<select id="role" name="role">
			<option selected="selected">User</option>
			<option>Manager</option>
			<option>Admin</option>			
			</select>
		</div>
		
		<input type="submit" value="Register">
	</form>

</body>
</html>
