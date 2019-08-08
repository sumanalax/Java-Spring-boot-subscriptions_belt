<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Subscription</title>
</head>
<body>
	<h1>Subscription: ${subscription.name}</h1>
	<form action="/subscriptions/${subscription.id}/update" method="POST">
		<input name="cost" type="text" placeholder="${subscription.cost}" value="${subscription.cost}" />
		<input type="submit" value="Edit" />
	</form>
	<form action="/subscriptions/${subscription.id}/delete" method="POST">
		<input type="submit" value="Delete" />
	</form>
</body>
</html>