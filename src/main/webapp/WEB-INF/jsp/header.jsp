<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <span style="text-align: left">
        <form action="/Blog/posts">
            <button type="submit">На главную</button>
        </form>
        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/user">
                <button type="submit">Мой профиль</button>
            </form>
        </c:if>
    </span>


    <span style="text-align: right">
        <c:if test="${empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/registration">
                <button type="submit">Регистрация</button>
            </form>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/login">
                <button type="submit">Войти</button>
            </form>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Выйти</button>
            </form>
        </c:if>
    </span>
</div>
