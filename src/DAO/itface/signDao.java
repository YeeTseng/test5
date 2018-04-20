package DAO.itface;

import bean.kindBean;

import java.util.List;

public interface signDao {
    int IsEmailExist (String email);
    int SignStepOne (String email,String password);
    int SignStepTwo (int id,String sex,String iconPath);
    int FindUserIdByEmail (String email);
    int UpdateUsernameById (int id,String username);
    int getUserDetailsById (int id);
    int updateUserIcon (String icon,int id);
    int updateUserSex (String sex,int id);
    int deleteUserById (int id);
    int deleteUserByCondition(int id,String email);
    List<kindBean> getAllKinds ();
    int updateUserInterested(int id,String kindIds);
}
