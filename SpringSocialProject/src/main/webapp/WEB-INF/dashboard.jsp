<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Dashboard</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>Welcome to your Dashboard!</h1>
	<hr>
	<h3>Welcome ${user.firstName} ${user.lastName}</h3>
	<a href="/signout">Sign Out</a>
	
	<h3>Ideas</h3>
	<table class="table">
	<thead>
		<tr>
			<th>Idea</th>
			<th>Created By:</th>
			<th>Likes</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${allMaterials}" var="materials">
			<tr>
				<td><a href="/ideas/${materials.id}">${materials.mText}</a></td>
				<td>${materials.user.firstName} ${materials.user.lastName}</td>
				<td>${materials.usersThatLiked.size()}</td>
				<td>
					<c:choose>
					<c:when test="${materials.usersThatLiked.contains(user)}">
					<a href="/unlike/${materials.id}">Un-Like</a>
					</c:when>
					<c:otherwise>
					<a href="/like/${materials.id}">Like</a>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
	
	<a href="/ideas/new">Create an Idea!</a>
</div>	
	
</body>
</html>