<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 22.01.2020
  Time: 13:47
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
        <fmt:message key="foodmenu.title"/>
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
<body>
<div class="limiter">
    <div class="container-table">
        <div class="wrap-table">
            <div class="table">
                <table>
                    <thead class="table-head">
                    <tr>
                        <th><fmt:message key="foodmenu.table.nameEn"/></th>
                        <th><fmt:message key="foodmenu.table.nameUa"/></th>
                        <th><fmt:message key="foodmenu.table.portion"/></th>
                        <th><fmt:message key="foodmenu.table.price"/></th>
                        <th><fmt:message key="foodmenu.button.addtoorder"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dish" items="${pageContext.request.getAttribute('dishes')}">
                        <tr>
                            <td><span><c:out value="${dish.nameEn}"/></span></td>
                            <td><span><c:out value="${dish.nameUa}"/></span></td>
                            <td><span><c:out value="${dish.portion}"/></span></td>
                            <td><span><fmt:formatNumber value="${dish.price}" type="currency"/></span></td>
                            <td><a href="${pageContext.request.contextPath}/buyproduct?id=${dish.id}">
                                <fmt:message key="foodmenu.button.addtoorder"/>
                            </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div align="center">
                <a class="btn" href="${pageContext.request.contextPath}/shoppingcart">
                    <fmt:message key="foodmenu.button.confirmation"/>
                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>