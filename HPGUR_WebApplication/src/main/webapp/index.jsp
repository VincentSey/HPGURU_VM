<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Testing JSP Page</title>
</head>
<body>
<h1>Hello JSP and Servlet!</h1>
<!-- Create a form with the action attribute to specific where to send the form-data when
the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE,
etc.) -->
<form action="HelloServlet" method="post">
Enter your name: <input type="text" name="yourName" size="20">
<!-- Implement submit button with type = submit to perform the request when clicked -->
<input type="submit" value="Call Servlet" />
</form>

</body>
</html>