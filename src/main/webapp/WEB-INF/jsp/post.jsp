<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${requestScope.post.get().title}</h1>
<p>
    ${requestScope.post.get().post_body}
</p>
</body>
</html>
