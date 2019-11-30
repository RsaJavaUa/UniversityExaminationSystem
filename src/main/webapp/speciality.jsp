<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>
        <fmt:message key="speciality.title"/>
    </title>
    <c:import url="/components/head.jsp"/>

</head>
<body>
<fmt:message key="speciality.title"/>
<c:import url="/components/header.jsp"/>
<%--main content of the page--%>
<%--THIS IS TEMPLATE OF SPECIALITY PAGE--%>
<table class="table table-striped">
    <thead>
    <tr>
        <c:forEach var="exam" items="${exams} ">
            <th scope="col">${exam.name}</th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="exam1" varStatus="loop" items="${exams}">
            <th scope="row">${loop.count}</th>
            <td><fmt:message key="exam.description.${exam1.name}"/></td>
        </c:forEach>
    </tr>
    <c:if test="${user.role = ADMIN}" >
    <tr>
        <td colspan="${fn:length(exams)}">
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
                    aria-expanded="false" aria-controls="collapseExample">
                Show Students subscribed to speciality
            </button>
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Surname</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="student" items="${students}">
                                <td>${student.firstName}</td>
                                <td>${student.lastName}</td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </td>
    </tr>
    </c:if>
    </tbody>
</table>


<%--end of the page content--%>
<footer class="footer">
    <c:import url="/components/footer.jsp"/>
</footer>
</body>
</html>
