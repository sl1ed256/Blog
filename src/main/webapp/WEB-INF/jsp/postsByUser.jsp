<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Статьи пользователя</h1>
    <ul>
        <c:forEach var="post" items="${requestScope.posts}">
            <li><a href=${pageContext.request.contextPath}/post?postId=${post.id}>${post.title}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
