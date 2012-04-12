<%--
  Created by IntelliJ IDEA.
  User: anbiniyar
  Date: 3/28/12
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.servletContext.contextPath}/resources/style.css" rel="stylesheet" media="screen" />
</head>
<body>

    <div class="container">
        <div>SUCCESS = ${success}</div>
        <div class="crawler">
            KILL ALL
                <form:form action="stop" method="DELETE">
                    <input type="submit" value="STOP ALL">
                </form:form>
        </div>
        <div class="crawler">
            KILL ALL
            <form:form action="start" method="POST">
                <input type="submit" value="START ALL">
            </form:form>
        </div>

        <c:forEach items="${status}" var="item" varStatus="index">
            <div class="crawler">${index.count} | ${item.name} | ${item.status} |
                <c:if test="${item.status == 'RUNNING'}">
                    <form:form action="stop/${index}" method="DELETE">
                       <input type="submit" value="STOP">
                    </form:form>
                </c:if>
                <c:if test="${item.status != 'RUNNING'}">
                    <form:form action="start/${index.count}" method="POST">
                        <input type="submit" value="START">
                    </form:form>
                </c:if>
            </div>
        </c:forEach>
    </div>

</body>
</html>