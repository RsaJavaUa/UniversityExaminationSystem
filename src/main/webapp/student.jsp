<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.css">
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
    </style>
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

    <ul class="form-inline my-2 my-lg-0">
        <li><a href="/login.do">Login</a></li>
    </ul>

</nav>

<div class="container">
    <h2>Students</h2>

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
    <p>footer content</p>
</footer>

<script src="webjars/jquery/3.4.1/jquery.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.js"></script>

</body>

</html>