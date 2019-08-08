<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<h1>Welcome, ${ user.name } </h1>
	<h3>Your current subscription: ${user.subscription.name}</h3>
	<h3>Package cost: ${user.subscription.cost}</h3>
	<h3>Due date: ${user.dueDate}</h3>
	<h3>Switch Package</h3>
	<form action="/subscriptions/change" method="POST" >
		<select name="subscription">
			<c:forEach items="${subscriptions}" var="subscription">
				<c:if test="${subscription.isAvailable()}">
					<option value="${subscription.id}"> ${subscription.name}</option>
				</c:if>
			</c:forEach>
		</select>
		<input type="submit" value="Switch Subscription" />
	</form>
</body>
</html>