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
    <title>Table Activities</title>
</head>
<body>
<jsp:include page="../header.jsp" />
    <div id="content">
        <c:if test="${not empty activities}">

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Short name</th>
                        <th>Subject name</th>
                    </tr>
                </thead>
                <c:forEach var="activity" items="${activities}">
                    <tr>
                        <td>${activity.getId()}</td>
                        <td>${activity.getDate()} </td>
                        <td>${activity.getShortName()} </td>
                        <td>${activity.getSubject().getName()} </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
