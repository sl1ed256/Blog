<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>Welcome to web blog!</title>
</head>
<body>
<%RequestDispatcher dispatch = request.getRequestDispatcher(request.getContextPath() + "/posts");
    dispatch.include(request, response);%>
</body>
</html>