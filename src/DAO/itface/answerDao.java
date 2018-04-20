package DAO.itface;

import bean.answerBean;

import java.util.List;

public interface answerDao {
    int saveAnswer (answerBean answerBean);
    List<answerBean> getAnswersByQuesId(int questionId);
}
