<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Список пользователей</h1>
<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <li>
            <a href=${pageContext.request.contextPath}/postsByUser?userId=${user.id}>${user.nickname}</a>
            <form action="${pageContext.request.contextPath}/users" method="post">
                <input type="hidden"  name="userId" value="${user.id}" />
                <input type="submit" value="Удалить" onclick="alert('Удалить?')"/>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
