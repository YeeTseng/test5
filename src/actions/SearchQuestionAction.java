package actions;

import DAO.itface.impl.questionDaoImpl;
import DAO.itface.impl.userDaoImpl;
import DAO.itface.questionDao;
import DAO.itface.userDao;
import bean.questionBean;
import bean.userBean;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class SearchQuestionAction extends ActionSupport {

    private String searchContent;
    private List<questionBean> questionList;
    private List<userBean> userBeanList;
    private String userId;
    private userBean userBean;
    //Dao
    private questionDao questionDao;
    private userDao userDao;
    @Override
    public String execute() throws Exception {
        if(searchContent != null && userId != null){
            userDao = new userDaoImpl();
            userBean = userDao.getUserById(Integer.parseInt(userId));
            this.setUserBean(userBean);
            if(getSearchContent()){
                return SUCCESS;
            }
        }
        return ERROR;
    }

    public boolean getSearchContent(){
        questionDao = new questionDaoImpl();
        try {
            questionList = questionDao.findQuestionByWords(searchContent);
            this.setQuestionList(questionList);
            String requesterIds = "";
            for (int i = 0;i<questionList.size();i++){
                requesterIds += questionList.get(i).getRequester() + ",";
            }
            if (requesterIds.length() != 0){
                requesterIds = "(" + requesterIds.substring(0,requesterIds.length()-1) + ")";
                userDao = new userDaoImpl();
                this.setUserBeanList(userDao.getUserListByIds(requesterIds));
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public List<questionBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<questionBean> questionList) {
        this.questionList = questionList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(bean.userBean userBean) {
        this.userBean = userBean;
    }

    public List<bean.userBean> getUserBeanList() {
        return userBeanList;
    }

    public void setUserBeanList(List<bean.userBean> userBeanList) {
        this.userBeanList = userBeanList;
    }
}
