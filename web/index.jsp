<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/11/27
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" href="css/common.css">
    <script type="text/javascript" src="js/jquery/jquery.1.9.1.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="js/sign.js"></script>
  </head>
  <body>
    <form id="loginForm" action="LoginAction">
      <table id="login-table" style="text-align: center">
        <tr>
          <td>用户名：</td>
          <td><input type="text" name="email"></td>
        </tr>
        <tr>
          <td>用户名：</td>
          <td><input type="password" name="password"></td>
        </tr>
        <tr>
          <td><a href="SignAction"><input type="button" value="注册"></a></td>
          <td><input type="submit" value="登录"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
