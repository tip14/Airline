<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="dashboard">
<head>
<link rel="stylesheet" href="${cssPath}" type="text/css">
<meta charset="utf-8">
<title>Dashboard | Airline Project</title>
</head>

<body>

	<%@include file="navigation.jsp"%>
	
	<section>
		<div class="dashboard-search">
			<form class="search-form" action="search" method="POST">
				<input type="search" placeholder="Search field" name="search-field">
				<input type="submit" value="Search">
			</form>
		</div>
		<div class="search-results">

			<p class="after-add-msg error-added">${notfound}</p>
			<p class="after-add-msg error-added">${notdeleted}</p>
			<p class="after-add-msg success-added">${sessionScope.deleted}</p>
			<c:remove var="deleted" scope="session" />

			<table>
				<tr>
					<th>#</th>
					<th>Model</th>
					<th>Capacity</th>
					<th>Build date</th>
					<th>Delete</th>
				</tr>

				<c:if test="${planesForDisplay ne null}">
					<c:set var="planes" value="${planesForDisplay}" />
				</c:if>
				
				<c:if test="${planesFound ne null}">
					<c:set var="planes" value="${planesFound}" />
				</c:if>

				<c:set var="count" value="1" />
				
				<c:forEach items="${planes}" var="plane">

					<tr>
						<td><c:out value="${count}" /></td>
						<td><c:out value="${plane.model}" /></td>
						<td><c:out value="${plane.capacity}" /></td>
						<td><c:out value="${plane.buildDate}" /></td>

						<td>
							<form method="POST" action="remove" class="delete-form">
								<input type="hidden" name="planeModelForDelete" value="${plane.model}"> 
								<input type="submit" class="delete-btn" value="X">
							</form>
						</td>
						
					</tr>
					
					<c:set var="count" value="${count+1}" />
					
				</c:forEach>

			</table>
		</div>
	</section>

</body>

</html>