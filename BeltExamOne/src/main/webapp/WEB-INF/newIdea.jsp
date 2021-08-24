<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter New Idea</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>Create a new Idea</h1>
	<form:form action="/ideas/new" method="post" modelAttribute="newIdea">
		<p>
			<form:label path="mText">Idea: </form:label>
			<form:errors path="mText"/>
			<form:input path="mText"/>		
		</p>
		<form:input type="hidden" value="${userId}" path="user"/>
		<input type="submit" value="Create"/>
	</form:form>
</div>
</body>
</html>