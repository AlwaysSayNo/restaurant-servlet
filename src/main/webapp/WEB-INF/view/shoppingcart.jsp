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
    <meta charset="UTF-8">
    <title>
        <fmt:message key="shoppingcart.title"/>
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
            <fmt:message key="shoppingcart.label.yourOrder"/>
        </div>
        <div class="wrap-table">
            <c:if test="${pageContext.request.getAttribute('errorMessage') != null}">
                <label>
                    <fmt:message key="shoppingcart.label.failureMessage"/>
                </label>
            </c:if>
            <c:if test="${pageContext.request.getAttribute('failureMessage') != null}">
                <label>
                    <fmt:message key="dish.label.failureMessage"/>
                </label>
            </c:if>
            <form method="post" action="${pageContext.request.contextPath}/saveorder">
                <div class="table">
                    <table>
                        <thead class="table-head">
                        <tr>
                            <th><fmt:message key="foodmenu.table.nameEn"/></th>
                            <th><fmt:message key="foodmenu.table.nameUa"/></th>
                            <th><fmt:message key="foodmenu.table.portion"/></th>
                            <th><fmt:message key="foodmenu.table.price"/></th>
                            <th><fmt:message key="order.table.quantity"/></th>
                            <th><fmt:message key="shoppingcart.button.updateQuantity"/></th>
                            <th><fmt:message key="shoppingcart.table.totalCost"/></th>
                            <th><fmt:message key="shoppingcart.button.delete"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:if test="${pageContext.request.getAttribute('order').isEmpty()}">
                                <td colspan="8">
                                    <fmt:message key="shoppingcart.label.noItems"/>
                                    <a href="${pageContext.request.contextPath}/foodmenu?page=1">
                                        <fmt:message key="foodmenu.title"/>
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                        <c:forEach var="order_unit" items="${pageContext.request.getAttribute('order').orderUnits}">
                            <tr>
                                <td><span><c:out value="${order_unit.dish.nameEn}"/></span></td>
                                <td><span><c:out value="${order_unit.dish.nameUa}"/></span></td>
                                <td><span><c:out value="${order_unit.dish.portion}"/></span></td>
                                <td><span><fmt:formatNumber value="${order_unit.dish.price}" type="currency"/></span>
                                </td>
                                <td><input id="quantity" type="number"><c:out value="${order_unit.quantity}"/></td>
                                <td><a><fmt:message key="shoppingcart.button.updateQuantity"/></a></td>
                                <td><span><fmt:formatNumber value="${order_unit.amount}" type="currency"/></span></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/shoppingcartremovedish?id=${order_unit.dish.id}">
                                        <fmt:message key="shoppingcart.button.delete"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div align="center">
                    <button class="btn" type="submit">
                        <fmt:message key="shoppingcart.button.confirmOrder"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>
