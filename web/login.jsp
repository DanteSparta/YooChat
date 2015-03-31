<%--
  Created by IntelliJ IDEA.
  yoochat.model.User: zw531129
  Date: 15/3/31
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/userLogin" method="post">
        <label>
            <input type="text" name="username">
        </label>
        <label>
            <input type="password" name="password">
        </label>
        <input type="submit" name="Login">
    </form>
    <h3>New face? <a href="${pageContext.request.contextPath}/register.jsp"${pageContext.request.contextPath}">Click to register</a> </h3>
  </body>
</html>
