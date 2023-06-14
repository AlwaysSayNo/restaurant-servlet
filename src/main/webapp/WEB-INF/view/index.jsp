<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 21.01.2020
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>
        <fmt:message key="main.page"/>
    </title>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          type="text/css"
          href="css/index.css"
    />
</head>
<body>
<jsp:include page="_menu.jsp"/>
<header class="header">
    <div class="brand-box">
        <span class="brand">
            <fmt:message key="index.label.brandName"/>
        </span>
    </div>

    <div class="text-box">
        <h1 class="heading-primary">
            <span class="heading-primary-main">
                    <fmt:message key="index.label.primaryHeading"/>
            </span>
            <span class="heading-primary-sub">
                    <fmt:message key="index.label.secondaryHeading"/>
            </span>
        </h1>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-white btn-animated">
            <fmt:message key="index.button.lestEat"/>
        </a>
    </div>
</header>
<jsp:include page="_footer.jsp"/>
</body>
</html>
