<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="username">Name:
        <input type="text" name="username" id="username" required>
    </label><br>
    <label for="email">Email:
        <input type="text" name="email" id="email" required>
    </label><br>
    <label for="password">Password:
        <input type="password" name="password" id="password" required>
    </label><br>
    <label for="about">About:
        <input type="text" name="about" id="about" required>
    </label><br>
    <label for="image">Image:
        <input type="file" name="image" id="image" required>
    </label><br>
    <select name="role" id="role">Role:
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    <button type="submit">Send</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>

</form>
</body>
</html>
