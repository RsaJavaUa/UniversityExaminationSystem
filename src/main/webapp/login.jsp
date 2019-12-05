<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <c:import url="/components/head.jsp"/>
</head>
<body>

<form action="/login"  method="post">
    <div><label> Email (as login) : <input type="text" name="email"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
<a href="/registration">
    <div >
        <button type="submit">To registration page</button>
    </div>
</a>
</body>
</html>
