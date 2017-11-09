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
    <title>Table TimeTable</title>
</head>
<body>
<jsp:include page="../header.jsp" />
    <div id="content">
        <c:if test="${not empty timeTables}">

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Begin date</th>
                        <th>End date</th>
                        <th>Date</th>
                        <th>Day of week</th>
                        <th>Start time</th>
                        <th>End time</th>
                        <th>Parity</th>
                        <th>Place</th>
                        <th>Type</th>
                        <th>Subject name</th>
                        <th>Teacher name</th>
                    </tr>
                </thead>
                <c:forEach var="timeTable" items="${timeTables}">
                    <tr>
                        <td>${timeTable.getId()}</td>
                        <td>${timeTable.getBeginDate()} </td>
                        <td>${timeTable.getEndDate()}</td>
                        <td>${timeTable.getDate()}</td>
                        <td>${timeTable.getDayOfWeek()} </td>
                        <td>${timeTable.getStartTime()}</td>
                        <td>${timeTable.getEndTime()} </td>
                        <td>${timeTable.getParity()}</td>
                        <td>${timeTable.getPlace()}</td>
                        <td>${timeTable.getType()}</td>
                        <td>${timeTable.getSubject().getName()}</td>
                        <td>${timeTable.getTeacher().getName()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
