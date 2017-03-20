<%--
  Created by IntelliJ IDEA.
  User: T.Cage
  Date: 2016/12/19
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
<form action="<c:url value='/ScholarServlet?'/>" method="get" id="searchForm">
    <input type="hidden" name="method" value="findByAdvisee" />
    Scholars:<input type="text" name="advisee" value="yan" /><br>
    <%--Pages:<input type="text" name="pc" value="1" /><br>--%>
    <input type="submit" value="search"/>
</form>

</body>
</html>
