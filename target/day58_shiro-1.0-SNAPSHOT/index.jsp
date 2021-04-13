<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>登录</h2>

<form action="/login.do" method="post">
    用户名<input type="text" name="username"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
<span>${msg}</span>

</body>
</html>
