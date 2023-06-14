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
        <fmt:message key="order.title"/>
    </title>
    <link rel="stylesheet" type="text/css" href="css/userstatistics.css">
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
                        <th><fmt:message key="foodmenu.table.nameUa"/></th>
                        <th><fmt:message key="foodmenu.table.nameEn"/></th>
                        <th><fmt:message key="foodmenu.table.portion"/></th>
                        <th><fmt:message key="foodmenu.table.price"/></th>
                        <th><fmt:message key="order.table.quantity"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order_unit" items="${pageContext.request.getAttribute('order').orderUnits}">
                        <tr>
                            <td><span><c:out value="${order_unit.dish.nameUa}"/></span></td>
                            <td><span><c:out value="${order_unit.dish.nameEn}"/></span></td>
                            <td><span><c:out value="${order_unit.dish.portion}"/></span></td>
                            <td><span><fmt:formatNumber value="${order_unit.dish.price}" type="currency"/></span></td>
                            <td><span><c:out value="${order_unit.quantity}"/></span></td>
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