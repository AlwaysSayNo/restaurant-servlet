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
        <fmt:message key="concreteuserstatistic.title"/>
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
            <div align="center">
                <h2>
                    <fmt:message key="concreteuserstatistic.label.allOrders"/>
                </h2>
            </div>
            <div class="table">
                <table>
                    <thead class="table-head">
                    <tr>
                        <th><fmt:message key="orderconfirmatiom.table.orderNumber"/></th>
                        <th><fmt:message key="shoppingcart.table.totalCost"/></th>
                        <th><fmt:message key="concreteuserstatistic.table.status"/></th>
                        <th><fmt:message key="concreteuserstatistic.table.created"/></th>
                        <th><fmt:message key="concreteuserstatistic.table.accepted"/></th>
                        <th><fmt:message key="concreteuserstatistic.table.ready"/></th>
                        <th><fmt:message key="concreteuserstatistic.table.paid"/></th>
                        <th><fmt:message key="differentpages.label.view"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${pageContext.request.getAttribute('orders').isEmpty()}">
                            <td colspan="8"><fmt:message key="concreteuserstatistic.label.noOrders"/></td>
                        </c:if>
                    </tr>
                    <c:forEach var="order" items="${pageContext.request.getAttribute('orders')}">
                        <tr>
                            <td><span><c:out value="${order.id}"/></span></td>
                            <td><span><fmt:formatNumber value="${order.amountTotal}" type="currency"/></span></td>
                            <td><span><c:out value="${order.status}"/></span></td>
                            <td><span>
                                <fmt:parseDate value="${order.created}" pattern="yyyy-MM-dd'T'HH:mm" var="createdParsed"
                                               type="both"/>
                                <fmt:formatDate value="${createdParsed}" pattern="dd-MM-yyyy HH:mm"/>
                            </span></td>
                            <td><span>
                                <fmt:parseDate value="${order.accepted}" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="acceptedParsed" type="both"/>
                                <fmt:formatDate value="${acceptedParsed}" pattern="dd-MM-yyyy HH:mm"/>
                            </span></td>
                            <td><span>
                                <fmt:parseDate value="${order.ready}" pattern="yyyy-MM-dd'T'HH:mm" var="readyParsed"
                                               type="both"/>
                                <fmt:formatDate value="${readyParsed}" pattern="dd-MM-yyyy HH:mm"/>
                            </span></td>
                            <td><span>
                                <fmt:parseDate value="${order.paid}" pattern="yyyy-MM-dd'T'HH:mm" var="paidParsed"
                                               type="both"/>
                                <fmt:formatDate value="${paidParsed}" pattern="dd-MM-yyyy HH:mm"/>
                            </span></td>
                            <td><a href="${pageContext.request.contextPath}/order?id=${order.id}">
                                <fmt:message key="differentpages.label.view"/>
                            </a></td>
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