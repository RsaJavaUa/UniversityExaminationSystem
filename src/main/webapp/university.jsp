<%--
  Created by IntelliJ IDEA.
  User: bronza
  Date: 19.11.19
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>university </title>
    <c:import url="components/head.jsp"/>
    <c:import url="components/header.jsp"/>
</head>
<body>
<%--<c:forEach var="speciality" items="${specialities}" varStatus="loop">--%>
<%--    <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--        <a class="dropdown-item" href="/${speciality.id}"> ${speciality.name}</a>--%>

<%--    </div>--%>
<%--</c:forEach>--%>


<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-interval="2000">
    <div class="carousel-inner">
        <div class="carousel-item active" >
            <img class="d-block w-100" src="https://ibb.co/JrLfDWn" alt="First slide">
        </div>
<c:forEach items="${namesAndImages}" var="nameAndImage">
        <div class="carousel-item">
            <a href="/speciality/${nameAndImage.key}"/>
            <img class="d-block w-100" src="${nameAndImage.value}"  alt="${nameAndImage.key}">
        </div>
</c:forEach>

    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<footer class="footer">
    <c:import url="components/footer.jsp"/>
</footer>
</body>
</html>
