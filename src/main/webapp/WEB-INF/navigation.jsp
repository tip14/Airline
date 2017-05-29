<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<ul class="menu">
		<li><a href="/airline"> Home </a></li>
		<li><a href="add"> Add </a></li>
		<li><a href="dashboard"> Dashboard </a></li>
		<c:if test="${logged==null}">
			<li id="login-btn">
			<a href="login"><c:out value="Login" /></a>
			</li>
		</c:if>
		<c:if test="${logged!=null}">
			<li id="logout-btn">
			<a href="logout"><c:out value="Logout" /></a>
			</li>
		</c:if>

	</ul>
</nav>