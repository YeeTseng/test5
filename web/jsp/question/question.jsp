<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/12/12
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Page</title>
</head>
<body>
    <div id="quesPart" style="width: 60%">
        <fieldset>
            <img src="<s:property value="userBean.iconPath" />" width="60px">
            <span><s:property value="userBean.username"/></span>
        </fieldset>
        <fieldset>
            <h4><s:property value="questionBean.questionName"/></h4>
            <p><s:property value="questionBean.content"/></p>
        </fieldset>
    </div>
</body>
</html>
