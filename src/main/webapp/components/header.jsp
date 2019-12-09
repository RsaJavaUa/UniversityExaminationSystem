<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:forEach var="speciality" varStatus="loop" items="${specialities}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${speciality.name}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach var="exam" varStatus="loop" items="${speciality.exams}">
                            <a class="dropdown-item" href="#">${exam.name}</a>
                            <div class="dropdown-divider"></div>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
            <li class="nav-item">
                <a class="nav-link" href="language?locale=en">Eng</a>
                <%--                <a class="nav-link" href="language?locale=fr">Fr</a>--%>
            </li>
            <li class="nav-item">
                <%--                <a class="nav-link" href="language?locale=en">Eng</a>--%>
                <a class="nav-link" href="language?locale=fr">Fr</a>
            </li>
        </ul>

        <c:choose>
        <c:when test="${user != null}">
            <ul class="nav navbar navbar-right">
                <li><a href="/login">Logout</a></li>
            </ul>
        </c:when>

        <c:otherwise>
            <ul class="nav navbar navbar-right">
                <li><a href="/login">Login</a></li>
            </ul>
        </c:otherwise>
        </c:choose>
    </div>

</nav>

</body>
</html>
