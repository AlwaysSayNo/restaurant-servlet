<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 22.01.2020
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message key="addfunds.title"/>
    </title>
    <link rel="stylesheet" type="text/css" href="css/footer.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
<jsp:include page="_menu.jsp"/>
<div align="center" action="/addfunds" class="container">
    <h1>
        <fmt:message key="addfunds.label.header"/>
    </h1>
    <p>
        <label>
            <fmt:message key="addfunds.label.currentfunds"/>
        </label>
        <label>
            <c:out value="${loginedUser.funds}"/>
        </label>
    </p>

    <div align="center">
        <c:if test="${pageContext.request.getAttribute('failureMessage') != null}">
            <label class="alert alert-danger" role="alert">
                <fmt:message key="addfunds.label.error"/>
            </label>
        </c:if>
    </div>
    <form action="${pageContext.request.contextPath}/addfunds" method="POST">
        <h2 class="form-signin-heading">
            <fmt:message key="authentication.label.enterData"/>
        </h2>
        <p>
            <label for="funds" class="sr-only"></label>
            <fmt:message key="addfunds.label.amount" var="ammounti18n"/>
            <input type="number" id="funds" name="funds" class="form-control" placeholder="${ammounti18n}"
                   required autofocus>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="addfunds.button.add"/>
        </button>
    </form>
</div>
</body>
</html>
