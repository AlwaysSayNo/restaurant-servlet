<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 22.01.2020
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<link rel="stylesheet" type="text/css" href="css/menu.css"/>
<div class="menu-row">
    <div class="menu-container">
        <a href="${pageContext.request.contextPath}/">
            <fmt:message key="home.title"/>
        </a>
        |
        <c:if test="${pageContext.request.isUserInRole('USER')}">
            <a href="${pageContext.request.contextPath}/foodmenu?page=1">
                <fmt:message key="foodmenu.title"/>
            </a>
            |
            <a href="${pageContext.request.contextPath}/shoppingcart">
                <fmt:message key="shoppingcart.title"/>
            </a>
            |
            <a href="${pageContext.request.contextPath}/addfunds">
                <fmt:message key="addfunds.title"/>
            </a>
            |
            <a href="${pageContext.request.contextPath}/billpaying">
                <fmt:message key="billpayind.title"/>
            </a>
            |
            <a href="${pageContext.request.contextPath}/concreteuserstatistic">
                <fmt:message key="concreteuserstatistic.title"/>
            </a>
            |
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
            <a href="${pageContext.request.contextPath}/orderconfirmation">
                <fmt:message key="orderconfirmation.title"/>
            </a>
            |
            <a href="${pageContext.request.contextPath}/billmaking">
                <fmt:message key="billmakint.title"/>
            </a>
            |
            <a href="${pageContext.request.contextPath}/userstatistics">
                <fmt:message key="userstatistics.title"/>
            </a>
            |
        </c:if>
    </div>
    <div class="header-container">
        <div class="header-bar">
            <a><c:out value="${loginedUser.username}"/></a>
            <c:if test="${loginedUser.username != null}">
                &nbsp;|&nbsp;
                <a href="${pageContext.request.contextPath}/logout">
                    <fmt:message key="authentication.label.logout"/>
                </a>
            </c:if>
            |
            <a href="?sessionLocale=en">
                <fmt:message key="lang.en"/>
            </a>
            |
            <a href="?sessionLocale=ua">
                <fmt:message key="lang.ua"/>
            </a>
            |
        </div>
    </div>
</div>
