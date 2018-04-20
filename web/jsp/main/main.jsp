<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ts
  Date: 2017/11/28
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Magazine</title>
    <link rel="stylesheet" href="js/jquery/jquery-ui.structure.css" >
    <link rel="stylesheet" href="js/jquery/jquery-ui.theme.css">
    <link rel="stylesheet" href="js/jquery/jquery-ui.css">
    <link rel="stylesheet" href="css/common.css">
    <script type="text/javascript" src="js/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
</head>
<body>
    <!-- 该页面可以考虑使用localStorage抓取用户经常点击的问题内容 -->
    <div id="main-head">
        <div id="logo">
            <a target="_self">
                <img src="img/timg.jpg" width="100px" height="45px">
            </a>
        </div>
        <div id="nav">
            <nav>
                <a target="_self">首页</a>
            </nav>
        </div>
        <div id="search">
            <form action="SearchQuestionAction">
                <input name="userId" type="hidden" value="<s:property value="userBean.userId"/>">
                <input id="questionSearch" name="searchContent" placeholder="搜索您感兴趣的内容" type="text">
                <input type="submit" class="ui-button" id="search_btn" style="margin-left: 3px; " value="搜索">
            </form>
        </div>
        <div id="user_label">
            <s:if test="userBean.iconPath != null">
                <input class="ui-button" type="button" value="提问" onclick="questionCreate()" style="margin-bottom: 25px">
                <s:if test="questionPage != null">
                    <input class="ui-button" type="button" value="写回答" onclick="answer()" style="margin-bottom: 25px">
                </s:if>
                <a href="">
                    <img src="<s:property value='userBean.iconPath'/>" width="45px" height="45px">
                </a>
            </s:if>
        </div>
    </div>
    <div id="main-body">
        <!-- 主要内容区域（问题列表、广告区域） -->
        <s:if test="userBean != null">
            <s:if test="questionList != null && questionList.size() > 0 && questionPage == null">
                <div id="quesListPart">
                    <s:iterator value="questionList">
                        <s:iterator value="userBeanList">
                            <s:if test="requester == userId">
                                <form action="questionAction" method="post" id="getQuestionForm<s:property value="questionId"/>">
                                    <input type="hidden" name="getQuestion" value="1">
                                    <input type="hidden" name="userId" value="<s:property value="userBean.userId"/>">
                                    <input type="hidden" name="requester" value="<s:property value="requester"/>">
                                    <input type="hidden" name="questionId" value="<s:property value="questionId"/>">
                                </form>
                                <fieldset>
                                    <a onclick="getQuestion(<s:property value="questionId"/>)">
                                        <fieldset style="margin-left: 10px;width: 95%;float: left">
                                            <div style="margin-left: 10px;width: 95%;border-bottom: 1px solid slategray;">
                                                <img src="<s:property value="iconPath"/>" width="60px">
                                                <span><s:property value="username"/></span>
                                            </div>
                                            <h3><s:property value="questionName"/></h3><br/>
                                            <p><s:property value="content"/></p>
                                        </fieldset>
                                        <span style="float: right">
                                        <s:if test="quesModityTimeSTR">
                                            于：<s:property value="quesModityTimeSTR"/>编辑。
                                        </s:if>
                                        <s:else>
                                            <s:property value="quesCreateTimeSTR"/>
                                        </s:else>
                                    </span>
                                    </a>
                                </fieldset>
                            </s:if>
                        </s:iterator>
                    </s:iterator>
                </div>
            </s:if>
            <s:elseif test="questionPage == 'true'">
                <script>
                    $(function () {
                        $("#quesPart").show();
                    });
                </script>
                <div id="quesPart" style="position: relative;margin-left:20%;float:left;width: 60%;display: none;background-color: #fff">
                    <fieldset>
                        <div style="width: 95%;border-bottom: 1px solid slategray;">
                            <img src="<s:property value="requesterBean.iconPath" />" width="60px">
                            <span><s:property value="requesterBean.username"/></span>
                        </div>
                        <h4><s:property value="questionBean.questionName"/></h4><br/>
                        <p><s:property value="questionBean.content"/></p>
                    </fieldset>
                    <s:if test="answerBeanList != null">
                        <s:iterator value="answerBeanList">
                            <input type="hidden" name="answerId" value="<s:property value="id"/>">
                            <input type="hidden" name="respondent" value="<s:property value="respondent"/>">
                            <fieldset style="margin-left: 5px;width: 97%;float: left;">
                                <div style="width: 100%;border-bottom: 1px solid slategray;">
                                    <img src="<s:property value="respondent_icon"/>" width="60px">
                                    <span><s:property value="respondent_name"/> : </span>
                                </div>
                                <p><s:property value="content"/></p><br/>
                                <s:if test="modifyTimeStr == null">
                                    <span style="float: right;"><s:property value="createTimeStr"/></span>
                                </s:if>
                                <s:else>
                                    <span style="float: right;"><s:property value="modifyTimeStr"/></span>
                                </s:else>
                            </fieldset>

                        </s:iterator>
                    </s:if>
                </div>
            </s:elseif>
            <s:else>
                <script>
                    $(function () {
                        questionCreate('showBtn');
                    });
                </script>
            </s:else>
                <div id="makeAQues" style="display: none">
                    <h1>目前还没有问题哟！要不要先提问呢？</h1><br/>
                    <input id="noQuesBtn" class="ui-button" type="button" value="提问" onclick="questionCreate('noQues')">
                    <form action="questionAction" id="quesForm" method="post">
                        <input type="hidden" name="createQuestion" value="1">
                        <fieldset id="quesCreateFieldset">
                            <legend>提问</legend>
                            <div id="questionDialog" style="display: none">
                                <!-- 由于该死的struts2对Ajax不友善，Dialog设计时直接放到body下无法提交表单的智障设定，
                                 为此，将dialog人为添加至form下的空div块里进行提交。但是奈何请求发出后，后台接收不到
                                 参数，故此，以下便是若干取值用的隐藏变量 -->
                                <input type="hidden" name="userName" value="<s:property value="userBean.username"/>">
                                <input type="hidden" name="userId" value="<s:property value="userBean.userId"/>">
                                <fieldset>
                                    <legend>提问者</legend>
                                    <div id="quesCreate_userPart">
                                        <img width="70px" src="<s:property value="userBean.iconPath"/>">
                                        <span>
                                        <s:property value="userBean.username"/>
                                    </span>
                                    </div>
                                </fieldset>

                                <div class="ui-widget">
                                    <fieldset>
                                        <legend>请选择问题种类</legend>
                                        <div>
                                            <s:iterator value="kindList" status="u">
                                                <label>
                                                    <input class="ui-checkboxradio-radio-label" name="kindChecked" type="radio" value="<s:property value="id"/>"><s:property value="kindName"/>
                                                </label>
                                                <s:if test="(#u.index + 1)%5 == 0 && #u.index > 0"><br></s:if>
                                            </s:iterator>
                                        </div>
                                    </fieldset>
                                </div>

                                <fieldset>
                                    <legend>标题</legend>
                                    <textarea class="ui-widget-content" style="width: 100%;min-height: 100px" placeholder="为您的问题命个名吧" name="questionName"></textarea>
                                </fieldset>
                                <fieldset>
                                    <legend>内容</legend>
                                    <textarea name="content" class="ui-widget-content" style="width: 100%;min-height: 50px" placeholder="问题的详细内容在这里哟"></textarea>
                                </fieldset>
                                <input type="submit" value="提问" class="ui-button" onclick="return emptyCheck()"/>
                                <input type="button" value="Cancel" class="ui-button" onclick="quesCreateCancel()">
                            </div>
                        </fieldset>
                    </form>
                </div>
        </s:if>

        <s:else>
            <form action="LoginAction" id="go_login"></form>
            <script>
                $(function () {
                   $("#go_login").submit();
                });
            </script>
        </s:else>
        <div style="display: none;" id="answer_part">
            <form action="answerAction" id="answerForm" method="post">
                <input type="hidden" name="userId" value="<s:property value="userBean.userId"/>">
                <input type="hidden" name="questionId" value="<s:property value="questionId"/>">
                <input type="hidden" name="saveAnswer" value="1">
                <fieldset>
                    <textarea name="answerContent"></textarea>
                </fieldset>
                <input type="submit" value="提交" class="ui-button" onclick="return answerCheck()">
                <input type="button" value="取消" class="ui-button" onclick="answerCancel()">
            </form>
        </div>
    </div>
</body>
</html>