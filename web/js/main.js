function questionCreate(noQues) {
    if(noQues == "noQues"){
        $("#makeAQues").show();
        $("#questionDialog").show();
        $("#quesListPart").hide();
    }else if(noQues == 'showBtn'){
        $("#makeAQues").show();
    }else {
        $("#makeAQues").show();
        $("#questionDialog").show();
        $("#quesListPart").hide();
        $("h1").hide();
        $("#noQuesBtn").hide();
    }
}
function quesCreateCancel() {
    $("#makeAQues").hide();
    $("#quesListPart").show();
}

function getQuestion(id) {
    $("#getQuestionForm"+id).submit();
}
function emptyCheck() {
    var kindCheck = $("input[name^=kindChecked]").is(":checked");
    var questionName = $("textarea[name^=questionName]").val();
    var content = $("textarea[name^=content]").val();
    if(!kindCheck){
        alert("请选择种类");
        return false;
    }else if(questionName == null){
        alert("标题不能为空");
        return false;
    }else if(content == null){
        alert("内容不能为空");
        return false;
    }else {
        return true;
    }
}
function answer() {
    $("#quesListPart").hide();
    $("#answer_part").show();
}
function answerCancel() {
    $("#answer_part").hide();
    $("#answer_part").find("textarea").val("");
}
function answerCheck() {
    var answerContent = $("textarea[name^=answerContent]").val();
    if(answerContent == null || answerContent == ""){
        alert("请填写您的回答内容");
        return false;
    }
    return true;
}