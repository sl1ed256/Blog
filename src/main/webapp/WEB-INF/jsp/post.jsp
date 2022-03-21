<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Статья</title>
</head>
<body>
<%@ include file="header.jsp" %>
<img width="150" height="150" src="${pageContext.request.contextPath}/image/${post.get().image}" alt=""><br>
<h1>${requestScope.post.get().title}</h1>
<p>
    ${requestScope.post.get().post_body}
</p>
<p style="text-align: right">
    ${requestScope.post.get().date_posted}
</p>

</body>
</html>
