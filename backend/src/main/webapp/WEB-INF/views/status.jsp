<%--
  Created by IntelliJ IDEA.
  User: anbiniyar
  Date: 3/28/12
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

    <div>
        <c:forEach items="${status}" var="item" varStatus="index">
            <div>${item.name} | ${item.status} | <a href="index/stop">Stop</a></div>
        </c:forEach>
    </div>

</body>
</html>