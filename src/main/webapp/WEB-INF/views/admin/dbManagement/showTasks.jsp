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
    <title>Table Tasks</title>
</head>
<body>
<jsp:include page="../header.jsp" />
    <div id="content">
        <c:if test="${not empty tasks}">

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Priority</th>
                        <th>Short name</th>
                        <th>Status</th>
                        <th>Subject name</th>
                    </tr>
                </thead>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>${task.getId()}</td>
                        <td>${task.getDate()} </td>
                        <td>${task.getDescription()}</td>
                        <td>${task.getPriority()}</td>
                        <td>${task.getShortName()} </td>
                        <td>${task.getStatus()}</td>
                        <td>${task.getSubject().getName()} </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
