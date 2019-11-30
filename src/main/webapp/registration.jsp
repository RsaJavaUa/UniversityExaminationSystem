
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title><fmt:message key="registration.title"/>
    </title>
    <c:import url="components/head.jsp"/>

</head>
<body>
<c:import url="components/header.jsp"/>
<%--main content of the page--%>

<form action="/login" method="get">
    <button type="Submit">Go To Login Page</button>
</form>
<div>
    <div>
        <div>
            <form autocomplete="off" action="/registration"
                  method="post" role="form">
                <h2>Registration Form</h2>
                <div>
                    <div>
                        <label for="email">E-mail (used as login)</label>
                        <input type="text" id="email" name ="email"  value="${user.email}" placeholder="E-mail"/>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="password">Password:</label>
                        <input id="password" name ="password" type="password" value="${user.password}"
                               placeholder="Password"/>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="firstName">Name:</label>
                        <input id="firstName" type="text" name="firstName" value="${user.firstName}"
                            placeholder="Name"/>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="lastName">Surname:</label>
                        <input id="lastName" type="text" name="lastName" value="${user.lastName}"
                               placeholder="Surname"/>
                    </div>
                </div>
                <div>
                    <div>
                        <label for="specialityName">Choose speciality:</label>
                        <select name="SpecialityName" id="specialityName">
                            <c:forEach items="${specialityNames}" var="name">
                                <option value="${name}">${name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div>
                    <div>
                        <button type="submit">Register User</button>
                    </div>
                </div>
            </form>
            <form action="/login">
                <div>
                    <button type="submit">To login page</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%--end of the content--%>
<footer class="footer">
    <c:import url="components/footer.jsp"/>
</footer>
</body>
</html>
