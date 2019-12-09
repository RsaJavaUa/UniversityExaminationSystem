<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/components/head.jsp"/>

</head>
<body>
<c:import url="/components/header.jsp"/>
<%--main content of the page--%>
<div class="container">
    <h2><fmt:message key="user.title.body"/><br></h2>

    <c:choose>
        <c:when test="${not empty studentsList}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <td>Number</td>
                    <td>Name</td>
                    <td>Last name</td>
                    <td>E-mail</td>
                    <td>Speciality</td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach var="user" items="${studentsList}" varStatus="loop">

                    <tr>
                        <td> ${loop.count}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.specialityName}</td>
                        <td><a href="/deletestudent/${user.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No students are registred yet
            </div>
        </c:otherwise>
    </c:choose>
</div>



<%--end of the page content--%>
<footer class="footer">
    <c:import url="/components/footer.jsp"/>
</footer>
</body>
</html>
