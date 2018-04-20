package DAO.itface.impl;

import DAO.itface.userDao;
import bean.userBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class userDaoImpl implements userDao {

    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private userBean userBean;

    @Override
    public List<userBean> getUserListByIds(String ids) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("user.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getUserListByIds") + ids;
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<userBean> userBeanList = new ArrayList<userBean>();
            while (resultSet.next()){
                userBean = new userBean();
                userBean.setUserId(resultSet.getInt("id"));
                userBean.setUsername(resultSet.getString("username"));
                userBean.setKindIds(resultSet.getString("kindids"));
                userBean.setSex(resultSet.getString("sex"));
                userBean.setEmail(resultSet.getString("email"));
                userBean.setIconPath(resultSet.getString("iconurl"));
                userBeanList.add(userBean);
            }
            return userBeanList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public userBean getUserById(int id) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("user.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getUserById");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                userBean = new userBean();
                userBean.setUserId(resultSet.getInt("id"));
                userBean.setUsername(resultSet.getString("username"));
                userBean.setKindIds(resultSet.getString("kindids"));
                userBean.setSex(resultSet.getString("sex"));
                userBean.setEmail(resultSet.getString("email"));
                userBean.setIconPath(resultSet.getString("iconurl"));
                return userBean;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
