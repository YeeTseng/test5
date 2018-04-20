<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/11/30
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Page</title>
    <script type="text/javascript" src="js/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/sign.js"></script>
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
    <form id="signForm" action="SignAction">
        <input type="hidden" name="signStep" value="1">
        <table>
            <tr>
                <td>注册邮箱：</td>
                <td><input type="email" name="userEmail"></td>
            </tr>
            <tr>
                <td>密    码：</td>
                <td><input type="password" name="signPassword"></td>
            </tr>
            <tr>
                <td>密码确认：</td>
                <td><input type="password" name="passwordConfirm"></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
                <td><a href="LoginAction"><input type="button" value="登录"></a></td>
            </tr>
        </table>
    </form>
</body>
</html>
