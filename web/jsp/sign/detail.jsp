<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/11/30
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail Page</title>
    <script type="text/javascript" src="js/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/sign.js"></script>
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
    <form action="SignAction" method="post" enctype="multipart/form-data">
        <input type="hidden" name="signStep" value="2">
        <table>
            <tr>
                <!-- 头像 -->
                <td>上传头像：</td>
                <td>
                    <input type="file" name="userIcon" accept="image/gif,image/jpeg,image/jpg">
                </td>
            </tr>
            <tr>
                <!-- 昵称 -->
                <td>用户名：</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <!-- 性别 -->
                <td colspan="2">
                    男：<input type="radio" name="sex" value="male">
                    女：<input type="radio" name="sex" value="female">
                </td>
            </tr>
            <tr>
                <!-- 提交 -->
                <td colspan="2">
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
