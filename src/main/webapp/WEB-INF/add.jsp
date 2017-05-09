<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="add">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${cssPath}" type="text/css">
<title>Add plane | Airline Project</title>
</head>

<body>

	<%@include file="navigation.jsp"%>

	<form action="add" method="POST">
		<div>
			<label for="model">Model:</label> 
			<input type="text" id="model" name="model">
		</div>
		<div>
			<label for="capacity">Capacity:</label> 
			<input type="number" id="capacity" name="capacity">
		</div>
		<div>
			<label for="build-date">Build date:</label>
			<input type="date" id="build-date" name="build-date">
		</div>
		<input type="submit" value="ADD">
	</form>

	<p class="after-add-msg success-added">${success}</p>
	<p class="after-add-msg error-added">${error}</p>

</body>
</html>
