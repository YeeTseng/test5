package actions;

import DAO.itface.impl.SignDaoImpl;
import DAO.itface.impl.mainDaoImpl;
import DAO.itface.impl.userDaoImpl;
import DAO.itface.mainDao;
import DAO.itface.signDao;
import DAO.itface.userDao;
import bean.kindBean;
import bean.questionBean;
import bean.userBean;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class MainAction extends ActionSupport {

    private userBean userBean;
    private mainDao dao;
    private signDao signDao;
    private userDao userDao;
    private List<questionBean> questionList;
    private List<kindBean> kindList;
    private List<userBean> userBeanList;


    @Override
    public String execute() throws Exception {
        if (userBean != null){
            dao = new mainDaoImpl();
            this.setUserBean(userBean);
            /*
             * TODO 关于用户问题列表的获取顺序：
             * 1、获取用户感兴趣的问题列表
             * 2、获取用户曾经回答、点赞、关注过的问题列表
             * 3、用户自己创建的问题列表。
             * 4、若以上均搜索为空，则提示用户提问。
             * 5、提问后再次发送请求至此。
             */
            questionList = dao.getQuestionsByKindIds(userBean.getKindIds());
            if (questionList == null || questionList.size() == 0){
                signDao = new SignDaoImpl();
                kindList = signDao.getAllKinds();
                if (kindList != null){
                    this.setKindList(signDao.getAllKinds());
                }
            }else {
                //获取问题列表及问题相关用户列表（TODO 2017/12/19 -- 截止目前，尚未加入回答相关信息）
                this.setQuestionList(dao.getQuestionsByKindIds(userBean.getKindIds()));
                String requesterIds = "";
                for (int i = 0;i<questionList.size();i++){
                    requesterIds += questionList.get(i).getRequester() + ",";
                }
                if (requesterIds.length() != 0){
                    requesterIds = "(" + requesterIds.substring(0,requesterIds.length()-1) + ")";
                    userDao = new userDaoImpl();
                    this.setUserBeanList(userDao.getUserListByIds(requesterIds));
                }
                signDao = new SignDaoImpl();
                kindList = signDao.getAllKinds();
                if (kindList != null){
                    this.setKindList(signDao.getAllKinds());
                }
            }
            return SUCCESS;
        }else {
            return ERROR;
        }
    }

    public bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(bean.userBean userBean) {
        this.userBean = userBean;
    }

    public List<questionBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<questionBean> questionList) {
        this.questionList = questionList;
    }

    public List<kindBean> getKindList() {
        return kindList;
    }

    public void setKindList(List<kindBean> kindList) {
        this.kindList = kindList;
    }

    public List<bean.userBean> getUserBeanList() {
        return userBeanList;
    }

    public void setUserBeanList(List<bean.userBean> userBeanList) {
        this.userBeanList = userBeanList;
    }
}
