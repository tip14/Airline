<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="index">
<c:set var="cssPath" scope="application" value="${pageContext.request.contextPath}/style.css" />
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${cssPath}" type="text/css">
<title>Home | Airline Project</title>
</head>
<body>

	<%@include file="navigation.jsp"%>
	
	<section>
		<article class="main-text">

			<h1>Airline Project</h1>
			
			<div class="above-text-block">				

				<p class="success-msg"><c:out value="${sessionScope.regSuccess}" /></p>
				<c:remove var="regSuccess" scope="session" />
				<p class="success-msg"><c:out value="${sessionScope.authorized}" /></p>
				<c:remove var="authorized" scope="session" />
				<p class="success-msg"><c:out value="${sessionScope.unauthorized}" /></p>
				<c:remove var="unauthorized" scope="session" />

				<a href="registration"><button class="reg-btn-btn">Registration</button></a>
			</div>
			
			<p>It is an Airline project provides information about airplanes,
				tickets etc.</p>
			<ul>
				<li>For adding smth use <a href="add">Add page</a>
				</li>
				<li>Removing operation is also provided, but accesable only for
					admins</li>
				<li><a href="dashboard">Dashboard</a> will help you in
					searching and listing of products</li>
			</ul>
						
		</article>
	</section>
	<div class="bg-img"></div>
</body>

</html>
