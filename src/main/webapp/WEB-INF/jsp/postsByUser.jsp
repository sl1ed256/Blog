<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Мои статьи</h1>
<a href="${pageContext.request.contextPath}/newPost">Создать новую статью</a>
<ul>
    <c:forEach var="post" items="${requestScope.posts}">
        <li><a href=${pageContext.request.contextPath}/post?postId=${post.id}>${post.title}</a></li>
        <form action="${pageContext.request.contextPath}/post" method="post">
            <input type="hidden" name="postId" value="${post.id}"/>
            <input type="submit" value="Удалить" onclick="alert('Удалить?')"/>
        </form>
    </c:forEach>
</ul>
</body>
</html>
