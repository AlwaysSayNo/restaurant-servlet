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
        <fmt:message key="billpayind.title"/>
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
            <fmt:message key="billpaying.label.yourBills"/>
        </div>
        <div class="wrap-table">
            <c:if test="${pageContext.request.getAttribute('failureMessage') != null}">
                <div align="center" class="alert alert-danger" role="alert">
                    <label>
                        <fmt:message key="order.label.failureMessage"/>
                    </label>
                </div>
            </c:if>
            <c:if test="${pageContext.request.getAttribute('notEnoughFundsMessage') != null}">
                <div align="center" class="alert alert-danger" role="alert">
                    <label>
                        <fmt:message key="paying.label.notEnoughFunds"/>
                    </label>
                </div>
            </c:if>
            <div class="table">
                <table>
                    <thead class="table-head">
                    <tr>
                        <th><fmt:message key="orderconfirmatiom.table.orderNumber"/></th>
                        <th><fmt:message key="shoppingcart.table.totalCost"/></th>
                        <th><fmt:message key="billpaying.button.payBill"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${pageContext.request.getAttribute('bills').isEmpty()}">
                            <td colspan="3"><fmt:message key="billpaying.label.noBills"/></td>
                        </c:if>
                    </tr>
                    <c:forEach var="bill" items="${pageContext.request.getAttribute('bills')}">
                        <tr>
                            <td><span><c:out value="${bill.id}"/></span></td>
                            <td><span><fmt:formatNumber value="${bill.totalCost}"/></span></td>
                            <td><a href="${pageContext.request.contextPath}/paybill?id=${bill.id}">
                                <fmt:message key="billpaying.button.payBill"/>
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