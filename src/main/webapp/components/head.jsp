<%--
  Created by IntelliJ IDEA.
  User: bronza
  Date: 17.11.19
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%--    have to remake title by bundle--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="message"/>
<title><fmt:message key="student.title"/></title>
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
