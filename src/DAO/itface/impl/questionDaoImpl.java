package DAO.itface.impl;

import DAO.itface.questionDao;
import bean.answerBean;
import bean.kindBean;
import bean.questionBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class questionDaoImpl implements questionDao {

    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private questionBean questionBean;

    @Override
    public List<answerBean> getAnswerInfoByQuesId(int quesId) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("question.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getAnswerInfoByQuesId");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,quesId);
            List<answerBean> list = new ArrayList<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                answerBean bean = new answerBean();
                bean.setId(resultSet.getInt("id"));
                bean.setContent(resultSet.getString("content"));
                bean.setQuestionId(resultSet.getInt("questionid"));
                bean.setRespondent(resultSet.getInt("respondent"));
                bean.setRespondent_name(resultSet.getString("respondent_name"));
                bean.setRespondent_icon(resultSet.getString("iconurl"));
                bean.setRespondent_sex(resultSet.getString("sex"));
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
    public int createQuestion(questionBean bean) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("question.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("questionCreate");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,bean.getRequester());
            statement.setString(2,bean.getQuestionName());
            statement.setString(3,bean.getContent());
            statement.setInt(4,bean.getKindId());
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
    public questionBean getQuestionByCondition(int requester, String questionName) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("question.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getQuestionByCondition");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,requester);
            statement.setString(2,questionName);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                questionBean = new questionBean();
                questionBean.setKindId(resultSet.getInt("kindid"));
                questionBean.setContent(resultSet.getString("content"));
                questionBean.setQuestionName(resultSet.getString("questionname"));
                questionBean.setRequester(resultSet.getInt("requester"));
                questionBean.setQuestionId(resultSet.getInt("questionid"));
                questionBean.setQuesCreateTime(resultSet.getTime("createtime"));
                if (resultSet.getTime("moditytime") != null){
                    questionBean.setQuesModifyTime(resultSet.getTime("moditytime"));
                }
                return questionBean;
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

    @Override
    public List<questionBean> getQuestionsOfUser(int requester) {
        return null;
    }

    @Override
    public questionBean getQuestionByName(String questionName) {
        return null;
    }

    @Override
    public List<questionBean> getQuestionByKind(int kindId) {
        return null;
    }

    @Override
    public questionBean getQuestionById(int questionId) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("question.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("getQuestionById");
            statement = conn.prepareStatement(sql);
            statement.setInt(1,questionId);
            resultSet = statement.executeQuery();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            while (resultSet.next()){
                questionBean = new questionBean();
                questionBean.setKindId(resultSet.getInt("kindid"));
                questionBean.setContent(resultSet.getString("content"));
                questionBean.setQuestionName(resultSet.getString("questionname"));
                questionBean.setRequester(resultSet.getInt("requester"));
                questionBean.setQuestionId(resultSet.getInt("questionid"));
                questionBean.setQuesCreateTime(resultSet.getTime("createtime"));
                questionBean.setQuesCreateTimeSTR(format.format(questionBean.getQuesCreateTime()));
                if (resultSet.getTime("modifytime") != null){
                    questionBean.setQuesModifyTime(resultSet.getTime("modifytime"));
                }
                return questionBean;
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

    @Override
    public int questionDelete(int requester, int questionId) {
        return 0;
    }

    @Override
    public int questionModify(questionBean bean) {
        return 0;
    }

    @Override
    public List<questionBean> findQuestionByWords(String words) {
        try {
            Properties sqlProp = new Properties();
            Properties sqlContent = new Properties();
            sqlProp.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            sqlContent.load(this.getClass().getClassLoader().getResourceAsStream("question.properties"));
            String url = sqlProp.getProperty("url");
            String name = sqlProp.getProperty("name");
            Class.forName(name);
            conn = DriverManager.getConnection(url);
            String sql = sqlContent.getProperty("findQuestionByWords");
            statement = conn.prepareStatement(sql);
            statement.setString(1,"%" + words + "%");
            statement.setString(2,"%" + words + "%");
            List<questionBean> list = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                questionBean bean = new questionBean();
                bean.setKindId(resultSet.getInt("kindid"));
                bean.setContent(resultSet.getString("content"));
                bean.setQuestionName(resultSet.getString("questionname"));
                bean.setRequester(resultSet.getInt("requester"));
                bean.setQuestionId(resultSet.getInt("questionid"));
                bean.setQuesCreateTime(resultSet.getDate("createtime"));
                bean.setQuesCreateTimeSTR(format.format(bean.getQuesCreateTime()));
                if (resultSet.getDate("modifytime") != null){
                    bean.setQuesModifyTime(resultSet.getDate("modifytime"));
                    bean.setQuesModifyTimeSTR(format.format(bean.getQuesModifyTime()));
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
    public List<questionBean> findQuestionByKinds(String kindIds) {
        return null;
    }
}
