<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
	<ul class="menu">
		<li><a href="/airline"> Home </a></li>
		<li><a href="add"> Add </a></li>
		<li><a href="dashboard"> Dashboard </a></li>
			
		<li>
		<c:if test="${authorized==null}">
					<a href="login"><c:out value = "Login"/></a>
			</c:if>
			<c:if test="${authorized!=null}">
					<a href="logout"><c:out value = "Logout"/></a>
			</c:if>
			</li>
	</ul>
</nav>