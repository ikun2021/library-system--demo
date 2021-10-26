<%--
  Created by IntelliJ IDEA.
  User: mayitbe
  Date: 2021/7/20
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form action="/login" method="post" >
    username: <input type="text" name="username"/> <br/>
    password:<input type="password" name="password"/> <br/>
    type:<input type="radio" name="type" value="reader" checked/>reader
         <input type="radio" name="type" value="admin"/>admin
    <input type="submit" value="login"/>
 </form>
</body>
</html>
