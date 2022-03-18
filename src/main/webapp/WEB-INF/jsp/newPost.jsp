<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая статья</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/newPost" method="post" enctype="multipart/form-data">
    <label for="title">Название статьи:
        <input type="text" name="title" id="title" required>
    </label><br>
    <label for="post_body">Текст статьи:
        <input type="text" name="post_body" id="post_body" required>
    </label><br>
    <label for="image">Фотография:
        <input type="file" name="image" id="image" required>
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
