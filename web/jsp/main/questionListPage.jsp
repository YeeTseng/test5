<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/11/29
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <script type="text/javascript" src="js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery/question.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <link rel="stylesheet" href="js/jquery/jquery-ui.structure.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui.theme.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui.css">
</head>
<body>
    <div id="questionListDiv">
        <%--<s:if test="questionList != null">
            <s:iterator value="questionList">
                <div id="<s:property value="questionBean.questionId"/>" style="width: 100%;height: 60px;border: 1px solid slategray" >
                    <table>
                        <tr>
                            <td><span><s:property value="questionBean.questionname"/></span></td>
                        </tr>
                        <tr>
                            <td><span><s:property value="questionBean.content"/></span></td>
                        </tr>
                    </table>
                </div>
            </s:iterator>
        </s:if>
        <s:else>
            <h1>目前还没有问题哟！要不要先提问呢？</h1><b/>
            <input class="ui-button ui-widget ui-corner-all" type="button" onclick="questionCreate()">
            <form action="questionAction" id="quesCreateForm">
                <div id="questionDialog" style="display: none">
                    <input type="hidden" value="<s:property value="userBean"/> ">
                    <h2>提问者：</h2><span><s:property value="userBean.username"/> </span><img width="100px" src="<s:property value="userBean.iconPath"/> ">

                    <div class="ui-widget">
                        <label><span>选择问题种类：</span></label>
                        <s:select list="kindList" name="questionKind" listCssClass="" listKey="id" listValue="kindName" listTitle="选择问题种类">
                        </s:select>
                        <input class="ui-button ui-widget ui-corner-all" type="submit" value="选好了">
                    </div>
                    <label>标题：</label>
                    <input class="text ui-widget ui-corner-all" type="text" name="questionname">
                    <label>内容：</label>
                    <input class="text ui-widget ui-corner-all" type="text" name="content">
                </div>
            </form>
        </s:else>--%>
    </div>
</body>
</html>
