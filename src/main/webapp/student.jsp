<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
    <c:import url="components/head.jsp"/>
</head>

<body>

<nav class="navbar navbar-light" style="background-color: #e3f2fd;">

    <a href="/" class="navbar-brand">Brand</a>
    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
    <a class="nav-link" href="#">Link</a>
    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
       aria-haspopup="true" aria-expanded="false">
        Dropdown
    </a>
    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="#">Action</a>
        <a class="dropdown-item" href="#">Another action</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Something else here</a>
    </div>
    <a class="nav-link disabled" href="#">Disabled</a>

    <ul class="nav navbar navbar-right">
        <li><a href="/login.do">Login</a></li>
    </ul>

</nav>

<div class="container">
    <h2><fmt:message key="student.title.body"/><br></h2>

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
                <c:forEach var="student" items="${studentsList}" varStatus="loop">

                    <tr>
                        <td> ${loop.count}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>${student.specialityName}</td>
                        <td><a href="/deletestudent/${student.id}">Delete</a>
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

<footer class="footer">
   <c:import url="components/footer.jsp"/>
</footer>


</body>

</html>