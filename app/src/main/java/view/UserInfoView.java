package view;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface UserInfoView {

    /**
     * 成功获取用户基本信息，如须在此方法中更新控件，请使用handle更新
     * @param userInfo
     */
    void userInfoGetSuccess(Object userInfo);

    /**
     * 获取用户基本信息失败，如须在此方法中更新控件，请使用handle更新
     * @param message
     */
    void userInfoGetFall(String  message);

}
