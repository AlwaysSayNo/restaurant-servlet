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
<meta charset="UTF-8">
<title>
    <fmt:message key="userstatistics.title"/>
</title>
<link rel="stylesheet"
      type="text/css"
      href="css/userstatistics.css"
/>
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
</head>
<body>
<jsp:include page="_menu.jsp"/>
<div class="limiter">
    <div class="container-table">
        <div class="wrap-table">
            <div class="table">
                <table>
                    <thead class="table-head">
                    <tr>
                        <th><fmt:message key="authentication.label.username"/></th>
                        <th><fmt:message key="signup.label.nameUa"/></th>
                        <th><fmt:message key="signup.label.nameEn"/></th>
                        <th><fmt:message key="userstatistics.table.funds"/></th>
                        <th><fmt:message key="userstatistics.table.ordersNumber"/></th>
                        <th><fmt:message key="userstatistics.table.ordersTotalCost"/></th>
                        <th><fmt:message key="userstatistics.table.registrationDate"/></th>
                        <th><fmt:message key="differentpages.label.view"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${pageContext.request.getAttribute('users').isEmpty()}">
                            <td colspan="8"><fmt:message key="userstatistics.label.noUsers"/></td>
                        </c:if>
                    </tr>
                    <c:forEach var="user" items="${pageContext.request.getAttribute('users')}">
                        <tr>
                            <td><span><c:out value="${user.username}"/></span></td>
                            <td><span><c:out value="${user.nameUA}"/></span></td>
                            <td><span><c:out value="${user.nameEN}"/></span></td>
                            <td><span><fmt:formatNumber value="${user.funds}" type="currency"/></span></td>
                            <td><span><c:out value="${user.ordersNumber}"/></span></td>
                            <td><span><c:out value="${user.ordersTotalCost}"/></span></td>
                            <td><span>
                                <fmt:parseDate value="${user.registrationDate}" pattern="yyyy-MM-dd" var="regDateParsed"
                                               type="date"/>
                                <fmt:formatDate value="${regDateParsed}" pattern="dd-MM-yyyy"/>
                            </span></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/concreteuserstatistic?id=${user.id}">
                                    <fmt:message key="differentpages.label.view"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>