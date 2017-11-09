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
    <title>Table Subjects</title>
</head>
<body>
<jsp:include page="../header.jsp" />
    <div id="content">
        <c:if test="${not empty teachers}">

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Family name</th>
                        <th>Father name</th>
                    </tr>
                </thead>
                <c:forEach var="teacher" items="${teachers}">
                    <tr>
                        <td>${teacher.getId()}</td>
                        <td>${teacher.getName()} </td>
                        <td>${teacher.getFamilyName()} </td>
                        <td>${teacher.getFatherName()} </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
