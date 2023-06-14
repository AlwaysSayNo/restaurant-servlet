<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 22.01.2020
  Time: 13:48
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>
        <fmt:message key="signup.title"/>
    </title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<div align="center" class="container">
    <form class="form-signup" action="${pageContext.request.contextPath}/signup" method="post">
        <h2 class="form-signin-heading">
            <fmt:message key="authentication.label.enterData"/>
        </h2>
        <c:if test="${pageContext.request.getAttribute('validationFailureMessage') != null}">
            <label class="alert alert-danger" role="alert">
                <fmt:message key="signup.label.error"/>
            </label>
        </c:if>
        <c:if test="${pageContext.request.getAttribute('errorMessage') != null}">
            <label class="alert alert-danger" role="alert">
                <fmt:message key="signup.label.alreadyRegistered"/>
            </label>
        </c:if>
        <p>
            <label for="nameEn" class="sr-only">Name En</label>
            <fmt:message key="signup.label.nameEn" var="nameENi18n"/>
            <input type="text" id="nameEn" name="nameEn" class="form-control" placeholder="${nameENi18n}"
                   required autofocus>
        </p>
        <p>
            <label for="nameUa" class="sr-only">Name Ua</label>
            <fmt:message key="signup.label.nameUa" var="nameUAi18n"/>
            <input type="text" id="nameUa" name="nameUa" class="form-control" placeholder="${nameUAi18n}"
                   required autofocus>
        </p>
        <p>
            <label for="username" class="sr-only">Username</label>
            <fmt:message key="authentication.label.username" var="usernamei18n"/>
            <input type="text" id="username" name="username" class="form-control"
                   placeholder="${usernamei18n}" required autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <fmt:message key="authentication.label.password" var="passwordi18n"/>
            <input type="password" id="password" name="password" class="form-control"
                   placeholder="${passwordi18n}" required>
        </p>
        <input name="_csrf" type="hidden" value="900d2f30-bf67-43a2-86f0-016a9d5914a9"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="signup.button.signUp"/>
        </button>
        <div class="row row justify-content-center">
            <div><p class="message">
                <fmt:message key="signup.label.isAlreadyRegistered"/>
            </p></div>
            <div><a href="${pageContext.request.contextPath}/login">
                <fmt:message key="signup.label.logIn"/>
            </a></div>
        </div>
    </form>
</div>
</body>
</html>