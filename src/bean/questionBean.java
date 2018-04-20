package bean;

import java.util.Date;

public class questionBean {

    private int requester;
    private int questionId;
    private String questionName;
    private String content;
    private int kindId;
    private Date quesCreateTime;
    private String quesCreateTimeSTR;
    private Date quesModifyTime;
    private String quesModifyTimeSTR;

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public int getRequester() {
        return requester;
    }

    public void setRequester(int requester) {
        this.requester = requester;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getQuesCreateTime() {
        return quesCreateTime;
    }

    public void setQuesCreateTime(Date quesCreateTime) {
        this.quesCreateTime = quesCreateTime;
    }

    public Date getQuesModifyTime() {
        return quesModifyTime;
    }

    public void setQuesModifyTime(Date quesModifyTime) {
        this.quesModifyTime = quesModifyTime;
    }

    public String getQuesCreateTimeSTR() {
        return quesCreateTimeSTR;
    }

    public void setQuesCreateTimeSTR(String quesCreateTimeSTR) {
        this.quesCreateTimeSTR = quesCreateTimeSTR;
    }

    public String getQuesModifyTimeSTR() {
        return quesModifyTimeSTR;
    }

    public void setQuesModifyTimeSTR(String quesModifyTimeSTR) {
        this.quesModifyTimeSTR = quesModifyTimeSTR;
    }
}
