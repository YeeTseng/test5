package actions;

import DAO.itface.answerDao;
import DAO.itface.impl.answerDaoImpl;
import DAO.itface.impl.questionDaoImpl;
import DAO.itface.impl.userDaoImpl;
import DAO.itface.questionDao;
import DAO.itface.userDao;
import bean.answerBean;
import bean.questionBean;
import bean.userBean;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class questionAction extends ActionSupport {

    //用户
    private userBean userBean;

    //创建变量
    private questionBean questionBean;
    private String userId;
    private String userName;
    private String kindChecked;
    private String questionName;
    private String content;
    private int createQuestion;
    private int getQuestion;

    //获取问题变量
    private String requester;
    private userBean requesterBean;
    private String questionId;
    private String questionPage;
    private List<answerBean> answerBeanList;

    //DAO
    private questionDao dao;
    private userDao userDao;
    private questionDao questionDao;
    private answerDao answerDao;


    @Override
    public String execute() throws Exception {
        if (createQuestion == 1){
            if(createQuestion()){
                return SUCCESS;
            }
        }else if (getQuestion == 1){
            if(goQuesPage()){
                return SUCCESS;
            }
        }
        return ERROR;
    }

    private boolean createQuestion (){
        questionBean = new questionBean();
        if (userId != null && questionName != null
                && content != null && kindChecked != null){

            questionBean.setRequester(Integer.parseInt(userId.trim()));
            questionBean.setQuestionName(questionName);
            questionBean.setContent(content);
            questionBean.setKindId(Integer.parseInt(kindChecked.trim()));
            questionBean.setQuesCreateTime(new Date());

            dao = new questionDaoImpl();
            userDao = new userDaoImpl();
            if(dao.createQuestion(questionBean) != 0){
                this.setQuestionBean(dao.getQuestionByCondition(Integer.parseInt(userId),questionName));
                this.setUserBean(userDao.getUserById(Integer.parseInt(userId)));
                this.setQuestionPage("true");
                //TODO 获取user以及question后，相应的应该获取关于该问题的回答（List）以及回答问题的User（List）
                return true;
            }
        }
        return false;
    }

    private boolean goQuesPage(){
        userDao = new userDaoImpl();
        questionDao = new questionDaoImpl();
        answerDao = new answerDaoImpl();
        if (userId != null && requester != null && questionId != null){
            userBean = userDao.getUserById(Integer.parseInt(userId));
            requesterBean = userDao.getUserById(Integer.parseInt(requester));
            questionBean = questionDao.getQuestionById(Integer.parseInt(questionId));
            List<answerBean> list = answerDao.getAnswersByQuesId(Integer.parseInt(questionId));
            if (list != null && list.size() > 0){
                this.setAnswerBeanList(list);
            }
            questionPage = "true";
            return true;
        }
        return false;
    }
    public bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(bean.userBean userBean) {
        this.userBean = userBean;
    }

    public String getKindChecked() {
        return kindChecked;
    }

    public void setKindChecked(String kindChecked) {
        this.kindChecked = kindChecked;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public bean.questionBean getQuestionBean() {
        return questionBean;
    }

    public void setQuestionBean(bean.questionBean questionBean) {
        this.questionBean = questionBean;
    }

    public String getQuestionPage() {
        return questionPage;
    }

    public void setQuestionPage(String questionPage) {
        this.questionPage = questionPage;
    }

    public int getCreateQuestion() {
        return createQuestion;
    }

    public void setCreateQuestion(int createQuestion) {
        this.createQuestion = createQuestion;
    }

    public int getGetQuestion() {
        return getQuestion;
    }

    public void setGetQuestion(int getQuestion) {
        this.getQuestion = getQuestion;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public bean.userBean getRequesterBean() {
        return requesterBean;
    }

    public void setRequesterBean(bean.userBean requesterBean) {
        this.requesterBean = requesterBean;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<answerBean> getAnswerBeanList() {
        return answerBeanList;
    }

    public void setAnswerBeanList(List<answerBean> answerBeanList) {
        this.answerBeanList = answerBeanList;
    }
}
