<%--
  Created by IntelliJ IDEA.
  User: qiush
  Date: 2021/3/26
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎${user.username}访问 - select.jsp
<hr>
<a href="/user/add.do">进行添加(add)操作</a><br><br>
<a href="/user/delete.do">进行删除(delete)操作</a><br><br>
<a href="/user/update.do">进行修改(update)操作</a><br><br>
<hr>
<a href="/logout.do">退出</a><br><br>
</body>
</html>
