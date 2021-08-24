<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit This Idea</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<h2><c:out value="${editMaterial.mText}"/></h2>
	<form:form action="/ideas/${editMaterial.id}/edit" method="POST" modelAttribute="editMaterial">
		<div class="form-group">
		<h5>Edit this Idea in the form Below</h5>
		<hr>
		<form:label path="mText">Idea Content:</form:label>
		<form:errors path="mText"/>
		<form:input path="mText"/>
		</div>
		<form:input type="hidden" value="${editMaterial.user.id}" path="user"/>
		<button class="btn btn-primary" type="submit">Update</button>
	</form:form>
	<p><a href="/ideas/${material.id}/delete">Delete</a></p>
	</div>
</body>
</html>
	