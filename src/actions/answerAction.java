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

import java.util.List;

public class answerAction extends ActionSupport {

    private String userId;
    private String questionId;
    private String answerContent;
    private int saveAnswer;
    private int modifyAnswer;
    private answerBean answerBean;
    private String questionPage;

    private userBean userBean;
    private questionBean questionBean;
    private List<answerBean> answerBeanList;

    private answerDao answerDao;
    private questionDao questionDao;
    private userDao userDao;

    @Override
    public String execute() throws Exception {
        if (saveAnswer == 1){
            if(saveAnswer()){
                return SUCCESS;
            }
        }
        if (modifyAnswer == 1){
            //TODO 编辑回答
        }
        return ERROR;
    }

    private boolean saveAnswer(){
        answerBean = new answerBean();
        answerBean.setRespondent(Integer.parseInt(userId));
        answerBean.setQuestionId(Integer.parseInt(questionId));
        answerBean.setContent(answerContent);
        answerDao = new answerDaoImpl();
        if (answerDao.saveAnswer(answerBean) == 1){
            userDao = new userDaoImpl();
            questionDao = new questionDaoImpl();
            userBean = userDao.getUserById(Integer.parseInt(userId));
            questionBean = questionDao.getQuestionById(Integer.parseInt(questionId));
            answerBeanList = answerDao.getAnswersByQuesId(Integer.parseInt(questionId));
            questionPage = "true";
            return true;
        }
        return false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getSaveAnswer() {
        return saveAnswer;
    }

    public void setSaveAnswer(int saveAnswer) {
        this.saveAnswer = saveAnswer;
    }

    public bean.answerBean getAnswerBean() {
        return answerBean;
    }

    public void setAnswerBean(bean.answerBean answerBean) {
        this.answerBean = answerBean;
    }

    public int getModifyAnswer() {
        return modifyAnswer;
    }

    public void setModifyAnswer(int modifyAnswer) {
        this.modifyAnswer = modifyAnswer;
    }

    public String getQuestionPage() {
        return questionPage;
    }

    public void setQuestionPage(String questionPage) {
        this.questionPage = questionPage;
    }

    public bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(bean.userBean userBean) {
        this.userBean = userBean;
    }

    public bean.questionBean getQuestionBean() {
        return questionBean;
    }

    public void setQuestionBean(bean.questionBean questionBean) {
        this.questionBean = questionBean;
    }

    public List<bean.answerBean> getAnswerBeanList() {
        return answerBeanList;
    }

    public void setAnswerBeanList(List<bean.answerBean> answerBeanList) {
        this.answerBeanList = answerBeanList;
    }
}
