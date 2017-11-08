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

<c:if test="${not empty data}">

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Faculties</th>
            </tr>
        </thead>
        <c:forEach var="university" items="${data}">
            <tr>
                <td>${university.getId()}</td>
                <td>${university.getName()} </td>
                <td>${university.getDescription()}</td>
                <td>
                    <c:forEach var="fac" items="${university.getFaculties()}">
                        <span>${fac.getName()}</span><br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>

</body>
</html>
