<%--
  Created by IntelliJ IDEA.
  User: zw531129
  Date: 15/3/31
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Register</title>
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
</body>
</html>
