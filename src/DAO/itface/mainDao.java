package DAO.itface;

import bean.questionBean;

import java.util.List;

public interface mainDao {
    List<questionBean> getQuestionsByKindIds (String kindIds);
}
