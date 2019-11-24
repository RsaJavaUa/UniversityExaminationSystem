<%--
  Created by IntelliJ IDEA.
  User: bronza
  Date: 17.11.19
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="components/head.jsp"/>
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">

    <a href="/" class="navbar-brand">SpecializationNameVariable</a>
    <a class="nav-link" href="#">List of contestants <span class="sr-only">(current)</span></a>
    <a class="nav-link" href="#">Link</a>
    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
       aria-haspopup="true" aria-expanded="false">
        Exams
    </a>
    <c:forEach var="exam" items="${exams}" varStatus="loop">
    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="/${exam.id}"> ${exam.name}</a>

    </div>
    </c:forEach>
    <a class="nav-link disabled" href="#">Disabled</a>

    <ul class="form-inline my-2 my-lg-0">
        <li><a href="/login.do">Login</a></li>
    </ul>

</nav>
</body>
</html>
