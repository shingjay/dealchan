<%--
  Created by IntelliJ IDEA.
  User: anbiniyar
  Date: 2/1/12
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Not Sorted</h1>
    <ol>
    <c:forEach items="${userList}" var="user" >
        <li>${user.username}</li>
    </c:forEach>
    </ol>

    <h1>Sorted</h1>
    <ol>
        <c:forEach items="${sortedUserList}" var="user" >
            <li>${user.username}</li>
        </c:forEach>
    </ol>

</body>
</html>