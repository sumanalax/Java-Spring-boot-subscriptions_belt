<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
</head>
<body>
	<h1>Admin Dashboard</h1>
	
	<h1>Customers</h1>
	<table border="1">
		<tr>
			<th>User Name</th>
			<th>Due Date</th>
			<th>Cost</th>
			<th>Subscription Name</th>
		</tr>
			
	<c:forEach items="${users}" var="user">
		<c:if test="${!user.isAdmin() }">
			<tr>
				<td>${user.name}</td>
				<td>${user.dueDate}</td>
				<td>${user.subscription.cost}</td>
				<td>${user.subscription.name}</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
	
	<h1>Subscriptions</h1>
	
	<table border="1">
		<tr>
			<th>Subscription</th>
			<th>Cost</th>
			<th>Available</th>
			<th>Subscribed Users</th>
			<th>Actions</th>
		</tr>
			
		<c:forEach items="${subscriptions}" var="subscription">
			<tr>
				<td>${subscription.name}</td>
				<td>${subscription.cost }</td>
				<td>${subscription.available }</td>
				<td>${subscription.users.size()}</td>
				
				<c:if test="${subscription.available}">
					<td>
						<form action="/subscriptions/${subscription.id}/toggle" method="POST">
							<input type="submit" value="Deactivate"/>
						</form>
						<a href="/subscriptions/${subscription.id}/edit"> Edit</a> 
					</td>
				</c:if>
				<c:if test="${!subscription.available }">
					<td>
					<form action="/subscriptions/${subscription.id}/toggle" method="POST">
						<input type="submit" value="Activate" />
					</form>
					<a href="/subscriptions/${subscription.id}/edit"> Edit</a> 
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

	<form action="/subscriptions" method="POST">
		<input required name="name" type="text" placeholder="Subscription Name"/>
		<input required name="cost" min="1" type="text" placeholder="Subscription Cost"/>
		<input type="submit" value="Create Subscription"/>
	</form>
	
</body>
</html>