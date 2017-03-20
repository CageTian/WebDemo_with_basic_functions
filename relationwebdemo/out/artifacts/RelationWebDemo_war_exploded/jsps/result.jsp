<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: T.Cage
  Date: 2016/12/19
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach items="${pb.beanList}" var="scholar">
        <li>
            <p><a href=<c:url value='/ScholarServlet?method=ScholarInfo&bid=${scholar.bid}'/> >advisee:${scholar.advisee}</a> </p><br>
            <p>advisor:${scholar.advisor}</p><br>
            <p>beginYear:${scholar.beginYear}</p><br>
        </li>
    </c:forEach>
</ul>
<div style="float:left; width: 100%; text-align: center;">
    <hr/>
    <br/>
    <%@include file="/jsps/pager/pager.jsp" %>
</div>
</body>
</html>
