<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Idea</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1><c:out value="${material.mText}"/></h1>
	<p>Created By: <c:out value="${material.user.firstName} ${material.user.lastName}"/></p>
	
	<h3>Users who liked your idea:</h3>
	<ol>
	<c:forEach items="${material.usersThatLiked}" var="u">
	<li>${u.firstName} ${u.lastName}</li>
	</c:forEach>
	</ol>
	
	<a href="/ideas/${material.id}/edit">Edit this Idea</a>
</div>	
</body>

</html>