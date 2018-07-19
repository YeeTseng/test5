package actions;

import DAO.itface.impl.userDaoImpl;
import DAO.itface.userDao;
import bean.userBean;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class userInfoAction extends ActionSupport {
    private userDao userDao;
    private List<userBean> users;
    private userBean user;

    @Override
    public String execute() throws Exception{
        userDao = new userDaoImpl();
        users = new ArrayList<>();
        String ids = "20,21,25,26,27";
        try {
            users = userDao.getUserListByIds(ids);
            if(users.size() >= 0){

            }
            return SUCCESS;
        }catch (Exception e){
            return ERROR;
        }
    }
}
