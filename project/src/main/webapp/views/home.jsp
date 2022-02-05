<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<spring:form action="/" modelAttribute="account" method="post">
<spring:input type="text" path="username"/><br>
<spring:input type="text" path="password"/><br>
<input type="submit" value="submit">
</spring:form>
<a href="/register">register here</a>
</body>
</html>