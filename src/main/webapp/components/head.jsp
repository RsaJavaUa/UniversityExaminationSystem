
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${value}"/>
<fmt:setBundle basename="${message}"/>
<title><fmt:message key="user.title"/></title>
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
