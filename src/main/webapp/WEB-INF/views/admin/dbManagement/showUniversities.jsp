<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: galkinivan
  Date: 07.11.2017
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table Universities</title>
</head>
<body>

<c:if test="${not empty testArr}">

    <ul>
        <c:forEach var="testValue" items="${testArr}">
            <li>${testValue}</li>
        </c:forEach>
    </ul>

</c:if>

</body>
</html>
