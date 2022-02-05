<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>registration page</title>
<style>
.error{
color: #ff0000;
}
.container{
width:350px;
top:50%;
left: 50%;
transform: translate(-50%,-50%);
position:absolute; 
}
</style>
</head>
<body>
<div class="container">
<spring:form  modelAttribute="account" method="post" align="center">
<spring:input type="hidden" path="id" id="id"/>

<label for="fname">FirstName</label>
<spring:input type="text" path="firstName" id="fname"/>
<br>

<label for="lname">LastName</label>
<spring:input type="text" path="lastName" id="lname"/>
<br>

<label for="name">UserName</label>
<spring:input type="text" path="username" id="username"/>
<spring:errors path="username" cssClass="error"/>
<br>

<label for="pass">PassWord</label>
<spring:input type="text" path="password" id="pass"/>
<spring:errors path="password" cssClass="error"/>
<br>

<label>Email</label>
<spring:input type="email" path="email" />
<spring:errors path="email" cssClass="error"/>
<br>

<label>Phone</label>
<spring:input type="tel" path="phone" pattern="[0-9]{10}"/>
<spring:errors path="phone" cssClass="error"/>
<br>
<c:choose>
<c:when test="${edit}">
<input type="submit" value="Update"/>
</c:when>
<c:otherwise>
<input type="submit" value="Register"/>
</c:otherwise>
</c:choose>
</spring:form>
</div>
</body>
</html>