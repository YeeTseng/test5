package DAO.itface;

import bean.userBean;

import java.util.List;

public interface userDao {
    userBean getUserById (int id);
    List<userBean> getUserListByIds (String ids);
}
