<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="speciality.title"/></title>
    <c:import url="components/head.jsp"/>
</head>
<body>
<c:forEach var="exam" items="${exams}" varStatus="loop">
    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="/${exam.id}"> ${exam.name}</a>
        <a class="dropdown-item" href="/${exam.id}"> ${exam.specialityName}</a>

    </div>

</c:forEach>
${exams}
</body>
</html>
