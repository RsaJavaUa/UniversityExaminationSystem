<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title><fmt:message key="university.title"/></title>
    <c:import url="/components/head.jsp"/>

</head>
<body>
<c:import url="/components/header.jsp"/>
<div>${user}</div>


<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-interval="2000">
    <div class="carousel-inner">
        <div class="carousel-item active" >
            <img class="d-block w-100" src="https://ibb.co/JrLfDWn" alt="First slide">
        </div>
<c:forEach items="${namesAndImages}" var="nameAndImage">
        <div class="carousel-item">
            <a href="/speciality/${nameAndImage.key}"/>
            <img class="d-block w-100" src="WEB-INF/images/overviewUnionBoydCenter_1920x760.jpg"  alt="${nameAndImage.key}">
<%--            ${nameAndImage.value}--%>
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
    <c:import url="/components/footer.jsp"/>
</footer>
</body>
</html>
