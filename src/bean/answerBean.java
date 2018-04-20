package bean;

import java.util.Date;

public class answerBean {
    private int id;
    private int respondent;
    private String respondent_name;
    private String respondent_icon;
    private String respondent_sex;
    private int questionId;
    private String content;
    private Date createTime;
    private String createTimeStr;
    private Date modifyTime;
    private String modifyTimeStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRespondent() {
        return respondent;
    }

    public void setRespondent(int respondent) {
        this.respondent = respondent;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRespondent_name() {
        return respondent_name;
    }

    public void setRespondent_name(String respondent_name) {
        this.respondent_name = respondent_name;
    }

    public String getRespondent_icon() {
        return respondent_icon;
    }

    public void setRespondent_icon(String respondent_icon) {
        this.respondent_icon = respondent_icon;
    }

    public String getRespondent_sex() {
        return respondent_sex;
    }

    public void setRespondent_sex(String respondent_sex) {
        this.respondent_sex = respondent_sex;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getModifyTimeStr() {
        return modifyTimeStr;
    }

    public void setModifyTimeStr(String modifyTimeStr) {
        this.modifyTimeStr = modifyTimeStr;
    }
}
