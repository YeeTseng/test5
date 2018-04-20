package DAO.itface;

import bean.userBean;

public interface LoginDao {

    userBean getUserByEmail (String email, String password);

}
