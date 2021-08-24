<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login or Register</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Sign In</h1>
			<p>${signinError}</p>
			<form method = "POST" action="/signin">
			<div class="form-group">
			<label>Email:</label>
			<input type="email" name="semail" class="form-control">
			</div>
			<div class="form-group">
			<label>Password:</label>
			<input type="password" name="spassword" class="form-control">
			</div>
			<button class="btn btn-primary" type="submit">Sign In</button>
			</form>
			<hr>
		<h1>Register</h1>
			<form:form action="/register" method="post" modelAttribute="user">
				<div class="form-group">
					<form:label path="firstName">First Name: </form:label>
					<form:errors path="firstName"/>
					<form:input class="form-control" path="firstName"/>
				</div>
				<div class="form-group">
					<form:label path="lastName">Last Name: </form:label>
					<form:errors path="lastName"/>
					<form:input class="form-control" path="lastName"/>
				</div>
				<div class="form-group">
					<form:label path="email">Email: </form:label>
					<form:errors path="email"/>
					<form:input type="email" class="form-control" path="email"/>
				</div>
				<div class="form-group">
					<form:label path="password">Password: </form:label>
					<form:errors path="password"/>
					<form:input type="password" class="form-control" path="password"/>
				</div>
				<div class="form-group">
					<form:label path="confirmPassword">Confirm Password: </form:label>
					<form:errors path="confirmPassword"/>
					<form:input type="password" class="form-control" path="confirmPassword"/>
				</div>
				<button class="btn btn-primary" type="submit">Submit User</button>
			</form:form>
	</div>
</body>
</html>