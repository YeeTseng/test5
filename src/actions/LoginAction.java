package actions;

import DAO.itface.LoginDao;
import DAO.itface.impl.LoginDaoImpl;
import bean.userBean;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

    private String email;
    private String password;
    private LoginDao dao;
    private userBean userBean;

    @Override
    public String execute () throws Exception {
        dao = new LoginDaoImpl();
        this.setUserBean(dao.getUserByEmail(email,password));
        if ( this.getUserBean() != null ){
            return SUCCESS;
        }else{
            return LOGIN;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(bean.userBean userBean) {
        this.userBean = userBean;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
