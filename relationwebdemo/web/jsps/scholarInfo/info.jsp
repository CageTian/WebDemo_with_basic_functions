<%--
  Created by IntelliJ IDEA.
  User: T.Cage
  Date: 2016/12/19
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
advisee:${requestScope.get("scholar").advisee};<br>
advisor:${requestScope.get("scholar").advisor};<br>
</body>
</html>
