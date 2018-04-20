package DAO.itface.impl;

import DAO.itface.signDao;
import bean.kindBean;
import bean.userBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static tools.BaseTool.getUUID;

public class SignDaoImpl implements signDao {

    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    @Override
    public int IsEmailExist(String email) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("isEmailExist");
            statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                return 1;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public int SignStepOne(String email, String password){
        int result = 0;
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("saveUser");
            statement = conn.prepareStatement(sql);
            String uuid = getUUID();
            statement.setString(1,uuid);
            statement.setString(2,email);
            statement.setString(3,password);
            result = statement.executeUpdate();
            if (result == 1){
                userBean user = new userBean();
                user.setEmail(email);
                user.setUsername(uuid);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int SignStepTwo(int id, String sex, String iconPath){
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("saveUserDetail");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,sex);
            statement.setString(3,iconPath);
            return statement.executeUpdate();
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
        return 0;
    }

    @Override
    public int FindUserIdByEmail(String email) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("findUserIdByEmail");
            statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return 0;
    }

    @Override
    public int UpdateUsernameById(int id,String username) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("updateUsernameById");
            statement = conn.prepareStatement(sql);
            statement.setString(1,username);
            statement.setInt(2,id);
            return statement.executeUpdate();
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
        return 0;
    }

    @Override
    public int getUserDetailsById(int id) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getUserDetailsById");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                return 1;
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
        return 0;
    }

    @Override
    public int updateUserIcon(String icon,int id) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("updateUserIcon");
            statement = conn.prepareStatement(sql);
            statement.setString(1,icon);
            statement.setInt(2,id);
            return statement.executeUpdate();
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
        return 0;
    }

    @Override
    public int updateUserSex(String sex,int id) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("updateUserSex");
            statement = conn.prepareStatement(sql);
            statement.setString(1,sex);
            statement.setInt(2,id);
            return statement.executeUpdate();
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
        return 0;
    }

    @Override
    public int deleteUserById(int id) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("deleteUserById");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            return statement.executeUpdate();
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
        return 0;
    }

    @Override
    public int deleteUserByCondition(int id, String email) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("deleteUserByCondition");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,email);
            return statement.executeUpdate();
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
        return 0;
    }

    @Override
    public List<kindBean> getAllKinds() {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getAllKinds");
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<kindBean> kindBeanList = new ArrayList<kindBean>();
            while (resultSet.next()){
                kindBean bean = new kindBean();
                bean.setId(resultSet.getInt("id"));
                bean.setKindName(resultSet.getString("kindname"));
                kindBeanList.add(bean);
            }
            return kindBeanList;
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
    public int updateUserInterested(int id, String kindIds) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("sign.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("updateUserInterested");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,kindIds);
            return statement.executeUpdate();
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
        return 0;
    }

}
