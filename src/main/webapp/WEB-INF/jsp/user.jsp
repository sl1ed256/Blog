<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<img width="150" height="150" src="${pageContext.request.contextPath}/image/${user.get().image}" alt=""><br>
<h1>${requestScope.user.get().nickname}</h1>
<p>
    ${requestScope.user.get().about}
</p>
<a href=${pageContext.request.contextPath}/updateUser>Изменить свои данные</a><br>
<a href=${pageContext.request.contextPath}/postsByUser?userId=${user.get().id}>Мои статьи</a>
</body>
</html>
