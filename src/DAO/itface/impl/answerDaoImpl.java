package DAO.itface.impl;

import DAO.itface.answerDao;
import bean.answerBean;
import bean.questionBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class answerDaoImpl implements answerDao{

    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    @Override
    public List<answerBean> getAnswersByQuesId(int questionId) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("answer.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getAnswersByQuesId");
            SimpleDateFormat m_format = new SimpleDateFormat("yyyy/MM/dd");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,questionId);
            resultSet = statement.executeQuery();
            List list = new ArrayList();
            while (resultSet.next()){
                answerBean bean = new answerBean();
                bean.setId(resultSet.getInt("id"));
                bean.setQuestionId(resultSet.getInt("questionid"));
                bean.setContent(resultSet.getString("content"));
                bean.setRespondent(resultSet.getInt("respondent"));
                bean.setCreateTime(resultSet.getDate("createtime"));
                bean.setCreateTimeStr(m_format.format(bean.getCreateTime()));
                bean.setRespondent_sex(resultSet.getString("sex"));
                bean.setRespondent_name(resultSet.getString("username"));
                bean.setRespondent_icon(resultSet.getString("iconurl"));
                java.sql.Date modify = resultSet.getDate("modifytime");
                if(modify != null){
                    bean.setModifyTime(modify);
                    bean.setModifyTimeStr(m_format.format(bean.getModifyTime()));
                }
                list.add(bean);
            }
            return list;
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
    public int saveAnswer(answerBean answerBean) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("answer.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("saveAnswer");
            statement = conn.prepareStatement(sql);
            Date now = new Date();
            statement.setInt(1,answerBean.getRespondent());
            statement.setInt(2,answerBean.getQuestionId());
            statement.setString(3,answerBean.getContent());
            statement.setDate(4,new java.sql.Date(now.getTime()));
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
