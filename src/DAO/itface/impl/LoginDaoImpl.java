package DAO.itface.impl;

import DAO.itface.LoginDao;
import bean.userBean;

import java.sql.*;
import java.util.Properties;

public class LoginDaoImpl implements LoginDao {

    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    public userBean user;

    @Override
    public userBean getUserByEmail(String email, String password) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("login.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getUserByEmail");
            statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                userBean bean = new userBean();
                bean.setEmail(resultSet.getString("email"));
                bean.setUserId(resultSet.getInt("id"));
                bean.setUsername(resultSet.getString("username"));
                bean.setSex(resultSet.getString("sex"));
                bean.setIconPath(resultSet.getString("iconurl"));
                bean.setKindIds(resultSet.getString("kindids"));
                this.setUser(bean);
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public userBean getUser() {
        return user;
    }

    public void setUser(userBean user) {
        this.user = user;
    }
}
