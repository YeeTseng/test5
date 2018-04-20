package DAO.itface.impl;

import DAO.itface.mainDao;
import bean.questionBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class mainDaoImpl implements mainDao {

    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    @Override
    public List<questionBean> getQuestionsByKindIds(String kindIds) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("main.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getQuestionsByKindIds");
            sql += "(" + kindIds + ")" + "ORDER by q.createtime desc";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<questionBean> questionList = new ArrayList<questionBean>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            while (resultSet.next()){
                questionBean bean = new questionBean();
                bean.setQuestionId(resultSet.getInt("questionid"));
                bean.setRequester(resultSet.getInt("requester"));
                bean.setKindId(resultSet.getInt("kindid"));
                bean.setQuestionName(resultSet.getString("questionname"));
                bean.setContent(resultSet.getString("content"));
                bean.setQuesCreateTime(resultSet.getDate("createtime"));
                bean.setQuesCreateTimeSTR(format.format(bean.getQuesCreateTime()));
                if (resultSet.getDate("modifytime") != null){
                    bean.setQuesModifyTime(resultSet.getTime("moditytime"));
                    bean.setQuesModifyTimeSTR(format.format(bean.getQuesModifyTime()));
                }
                questionList.add(bean);
            }
            return questionList;
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
