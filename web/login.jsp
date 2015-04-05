<%--
  Created by IntelliJ IDEA.
  User: zw531129
  Date: 15/4/5
  Time: 14:02
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
         nickname: <input type="text" name="username">
      </label>
      <input type="submit" value="login">
  </form>
</body>
</html>
