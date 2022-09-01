<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<H1>Registration Form</H1>

<form action="RegisterServlet" method="post">
    Name: <input type = "text" name="userName">
    Password: <input type="password" name="password">
    Email: <input type ="text" name="email">
    Language: <select name="language">
        <option>English</option>
        <option>Spanish</option>
        <option>French</option>
        </select>
    Login_userid: <input type = "text" name="Login_userid">
    address: <input type = "text" name="address">
    reset: <input type = "text" name="reset">
        <input type="submit" value="Call Servlet "/>      
</form>

</body>
</html>