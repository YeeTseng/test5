package actions;

import DAO.itface.impl.SignDaoImpl;
import DAO.itface.signDao;
import bean.kindBean;
import bean.userBean;
import bean.warningBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import tools.BaseTool;


import java.io.File;
import java.util.List;
import java.util.Map;

public class SignAction extends ActionSupport implements SessionAware {
    //注册步骤
    private String signStep;
    //第一步用户信息
    private String userEmail;
    private String signPassword;
    private String passwordConfirm;
    //第二步用户信息
    private File userIcon;
    private String userIconFileName;
    private String userIconContentType;
    private String username;
    private String sex;
    private userBean userBean;
    //第三步：种类List & 用户所选kinds
    private List<kindBean> kindList;
    private List<String> kindChecked;
    //DAO & Session
    private signDao dao;
    private Map session;
    //SIGN WARING
    private warningBean warning = new warningBean();

    @Override
    public String execute() throws Exception {
        boolean successfully = false;
        if(signStep != null){
            switch (signStep){
                //检查邮箱是否可用，可用即注册
                case "1":
                    successfully = stepOne();
                    //若邮箱可用，进行注册
                    if (successfully){
                        if (stepOnePointFive()){
                            return "stepTwo";
                        }else {
                            return ERROR;
                        }
                    }
                    break;
                //存入用户基本信息
                case "2":
                    successfully = stepTwo();
                    if (successfully){
                        successfully = stepThree();
                        if (successfully){
                            return "stepThree";
                        }else{
                            return "kindListError";
                        }
                    }else {
                        if (warning.getSIGN_WARNING_USER_DETAIL_EXIST() == 1){
                            return ERROR;
                        }
                        return "stepTwoError";
                    }
                //用户关注项存入
                case "3":
                    successfully = stepThree();
                    if (successfully){
                        return "signExcept";
                    }else {
                        return "saveInterestedError";
                    }
            }
        }
        if (successfully){
            return SUCCESS;
        }else {
            return ERROR;
        }
    }

    /**
     *
     * @return
     * @throws Exception
     * 检查邮箱是否已注册
     *
     */
    public boolean stepOne() throws Exception {
        dao = new SignDaoImpl();
        int result = dao.IsEmailExist(userEmail);
        switch (result){
            //邮箱未注册，可以使用
            case 0:
                return true;
            //邮箱已注册，不可用
            case 1:
                return false;
        }
        //默认不可用
        return false;
    }

    /**
     *
     * @return
     * @throws Exception
     * 存入邮箱地址及密码
     *
     */
    public boolean stepOnePointFive() throws Exception {
        dao = new SignDaoImpl();
        userBean user = this.session.get("user_info") == null ? new userBean() : (userBean) this.session.get("user_info");
        user.setEmail(userEmail);
        this.session.put("user_info",user);
        int result = dao.SignStepOne(userEmail,signPassword);
        switch (result){
            //注册失败
            case 0:
                return false;
            //注册成功
            case 1:
                return true;
        }
        return false;
    }

    /**
     * 1、上传头像
     * 2、通过邮箱查询用户id
     * 3、修改用户名
     * 4、存入用户详细信息
     * @return
     * @throws Exception
     */
    public boolean stepTwo() throws Exception {
        // 1、上传头像
        BaseTool tool = new BaseTool();
        String iconPath = tool.uploadFile(userIcon,userIconFileName,userIconContentType);
        // 2、存入用户自定义用户名、性别、头像地址
        dao = new SignDaoImpl();
        /*
         * 1、查询用户id
         * 2、根据id修改用户名
         * 3、存入用户详情
         */
        //查询用户id
        int userId = dao.FindUserIdByEmail(userEmail == null ? ((userBean)this.session.get("user_info")).getEmail() : userEmail);
        if (userId != 0){
            //2、修改用户名
            int isUsernameChanged = dao.UpdateUsernameById(userId,username);
            while (isUsernameChanged != 0){
                //3、存入详情（验证该用户是否存在）
                int detailExist = dao.getUserDetailsById(userId);
                switch (detailExist){
                    //用户详情不存在，可以存入
                    case 0:
                        int result = dao.SignStepTwo(userId,sex,iconPath);
                        switch (result){
                            //不成功
                            case 0:
                                return false;
                            //成功
                            case 1:
                                userBean bean = (userBean) this.session.get("user_info");
                                bean.setUsername(username);
                                bean.setIconPath(iconPath);
                                bean.setUserId(userId);
                                bean.setSex(sex);
                                this.session.put("user_info",bean);
                                this.setUserBean(bean);
                                return true;
                        }
                    //用户详情存在，不予存入并删除该用户第一步存入信息，跳转至注册第一步重新注册。
                    case 1:
                        warning.setSIGN_WARNING_USER_DETAIL_EXIST(1);
                        LogManager.getLogger().warn("注册邮箱为："
                                + userEmail == null ? ((userBean)this.session.get("user_info")).getEmail() : userEmail
                                + "用户ID为："
                                + userId
                                + "的用户在 "
                                + this.getClass().getName()
                                + "方法中存入信息时遇到坏数据。具体调用方法为：getUserDetailsById( id )");
                        dao.deleteUserByCondition(userId,userEmail == null ? ((userBean)this.session.get("user_info")).getEmail() : userEmail);
                        return false;
                }
            }
        }
        return false;
    }

    /**
     * 1、选择感兴趣的种类
     * 2、存入所选种类
     * @return
     * @throws Exception
     */
    public boolean stepThree() throws Exception {
        // 判断是否为用户选择后发送的请求，若不是，则获取所有种类供用户选择
        String kindIds = "";
        if (kindChecked != null && kindChecked.size() != 0){
            //遍历所选种类id，生成字符串并存入种类信息
            for (int i=0;i<kindChecked.size();i++){
                kindIds += kindChecked.get(i) + ",";
            }
            kindIds = kindIds.substring(0,kindIds.length()-1);
            userBean bean = (userBean)this.session.get("user_info");
            int userId = bean.getUserId();
            switch (dao.updateUserInterested(userId,kindIds)){
                //存入不成功
                case 0:
                    bean.setKindIds(kindIds);
                    this.setUserBean(bean);
                    this.session.put("user_info",bean);
                    return false;
                //成功存入感兴趣的种类
                case 1:
                    return true;
            }
        }else {
            dao = new SignDaoImpl();
            if (dao.getAllKinds() != null && dao.getAllKinds().size() != 0){
                this.setKindList(dao.getAllKinds());
                return true;
            }
        }
        return false;
    }

    public String getSignStep() {
        return signStep;
    }

    public void setSignStep(String signStep) {
        this.signStep = signStep;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSignPassword() {
        return signPassword;
    }

    public void setSignPassword(String signPassword) {
        this.signPassword = signPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public File getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(File userIcon) {
        this.userIcon = userIcon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserIconFileName() {
        return userIconFileName;
    }

    public void setUserIconFileName(String userIconFileName) {
        this.userIconFileName = userIconFileName;
    }

    public String getUserIconContentType() {
        return userIconContentType;
    }

    public void setUserIconContentType(String userIconContentType) {
        this.userIconContentType = userIconContentType;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(bean.userBean userBean) {
        this.userBean = userBean;
    }

    public List<kindBean> getKindList() {
        return kindList;
    }

    public void setKindList(List<kindBean> kindList) {
        this.kindList = kindList;
    }

    public List<String> getKindChecked() {
        return kindChecked;
    }

    public void setKindChecked(List<String> kindChecked) {
        this.kindChecked = kindChecked;
    }
}
