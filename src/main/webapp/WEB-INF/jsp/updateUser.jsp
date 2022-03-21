<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/updateUser" method="post" enctype="multipart/form-data">
    <label for="username">Имя:
        <input type="text" name="username" id="username" required>
    </label><br>
    <label for="about">About:
        <input type="text" name="about" id="about" required>
    </label><br>
    <label for="image">Фотография:
        <input type="file" name="image" id="image" required>
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
