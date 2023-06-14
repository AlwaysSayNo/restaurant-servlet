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
        <fmt:message key="billmakint.title"/>
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
        <div class="page-title" align="center">
            <fmt:message key="billmaking.label.userOrders"/>
        </div>
        <div class="wrap-table">
            <c:if test="${pageContext.request.getAttribute('failureMessage') != null}">
                <div align="center" class="alert alert-danger" role="alert">
                    <label>
                        <fmt:message key="order.label.failureMessage"/>
                    </label>
                </div>
            </c:if>

            <div class="table">
                <table>
                    <thead class="table-head">
                    <tr>
                        <th><fmt:message key="orderconfirmatiom.table.orderNumber"/></th>
                        <th><fmt:message key="authentication.label.username"/></th>
                        <th><fmt:message key="orderconfirmatiom.table.totalQuantity"/></th>
                        <th><fmt:message key="shoppingcart.table.totalCost"/></th>
                        <th><fmt:message key="orderconfirmatiom.table.creationDate"/></th>
                        <th><fmt:message key="billmaking.button.makeBill"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${pageContext.request.getAttribute('orders').isEmpty()}">
                            <td colspan="6"><fmt:message key="billmaking.label.noOrders"/></td>
                        </c:if>
                    </tr>
                    <c:forEach var="order" items="${pageContext.request.getAttribute('orders')}">
                        <tr>
                            <td><span><c:out value="${order.id}"/></span></td>
                            <td><span><c:out value="${order.user.username}"/></span></td>
                            <td><span><c:out value="${order.quantityTotal}"/></span></td>
                            <td><span><fmt:formatNumber value="${order.amountTotal}" type="currency"/></span></td>
                            <td><span>
                                <fmt:parseDate value="${order.created}" pattern="yyyy-MM-dd'T'HH:mm" var="createdParsed"
                                               type="both"/>
                                <fmt:formatDate value="${createdParsed}" pattern="dd-MM-yyyy HH:mm"/>
                            </span></td>
                            <td><a href="${pageContext.request.contextPath}/makebill?id=${order.id}">
                                <fmt:message key="billmaking.button.makeBill"/>
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