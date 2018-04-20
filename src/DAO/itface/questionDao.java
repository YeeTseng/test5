package DAO.itface;

import bean.answerBean;
import bean.questionBean;

import java.util.List;

public interface questionDao {
    int createQuestion (questionBean bean);
    questionBean getQuestionByCondition (int creatorId, String questionName);
    List<questionBean> getQuestionsOfUser (int creatorId);
    List<questionBean> getQuestionByKind (int kindId);
    questionBean getQuestionByName (String questionName);
    questionBean getQuestionById (int questionId);
    int questionDelete (int creatorId,int questionId);
    int questionModify (questionBean bean);
    List<questionBean> findQuestionByWords (String words);
    List<questionBean> findQuestionByKinds (String kindIds);
    List<answerBean> getAnswerInfoByQuesId (int quesId);
}
