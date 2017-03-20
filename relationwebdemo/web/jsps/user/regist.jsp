<%--
  Created by IntelliJ IDEA.
  User: T.Cage
  Date: 2017/1/23
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/regist.css'/>">

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<c:url value='/js/regist.js'/>"></script>
</head>

<body>
<div id="divMain">
    <div id="divTitle">
        <span id="spanTitle">新用户注册</span>
    </div>
    <div id="divBody">
        <form action="<c:url value='/RegisteServlet'/>" method="post" id="registForm">
            <input type="hidden" name="method" value="regist"/>
            <table id="tableForm">
                <tr>
                    <td class="tdText">用户名：</td>
                    <td class="tdInput">
                        <input class="inputClass" type="text" name="username" id="username" value="${form.username }"/>
                    </td>
                    <td class="tdError">
                        <label class="errorClass" id="usernameError">${errors.username }</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">登录密码：</td>
                    <td>
                        <input class="inputClass" type="password" name="password" id="password" value="${form.password }"/>
                    </td>
                    <td>
                        <label class="errorClass" id="passwordError">${errors.password }</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">确认密码：</td>
                    <td>
                        <input class="inputClass" type="password" name="repassword" id="repassword" value="${form.repassword }"/>
                    </td>
                    <td>
                        <label class="errorClass" id="repasswordError">${errors.repassword}</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">Email：</td>
                    <td>
                        <input class="inputClass" type="text" name="email" id="email" value="${form.email }"/>
                    </td>
                    <td>
                        <label class="errorClass" id="emailError">${errors.email}</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">验证码：</td>
                    <td>
                        <input class="inputClass" type="text" name="verifyCode" id="verifyCode" value="${form.verifyCode }"/>
                    </td>
                    <td>
                        <label class="errorClass" id="verifyCodeError">${errors.verifyCode}</label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div id="divVerifyCode"><img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>" onclick="changeVerify()"/></div>
                    </td>
                    <td>
                        <label><a href="javascript:changeVerify()">换一张</a></label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="image" src="<c:url value='/resource/regist1.jpg'/>" id="submitBtn"/>
                    </td>
                    <td>
                        <label></label>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
