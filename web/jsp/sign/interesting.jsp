<%@ taglib prefix="ww" uri="/struts-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/12/6
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Interesting</title>
    <script type="text/javascript" src="js/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/interesting.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <link rel="stylesheet" href="js/jquery/jquery-ui.structure.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui.theme.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui.css">
    <link rel="stylesheet" href="css/common.css">
</head>
<body>

<div>
    <div class="ui-widget">
        <form action="SignAction">
            <input type="hidden" name="signStep" value="3">
            <s:iterator value="kindList">
                <label><input name="kindChecked" type="checkbox" value="<s:property value="kindBean.id"/>"><s:property value="kindBean.kindName"/></label>
            </s:iterator>
            <input class="ui-button ui-widget ui-corner-all" type="submit" value="选好了">
        </form>
    </div>
</div>
</body>
</html>