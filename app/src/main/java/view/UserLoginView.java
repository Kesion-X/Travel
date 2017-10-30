package view;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface UserLoginView {

    /**
     * 登录成功，若要更新UI请在Handle中更新
     */
    void loginSuccess();
    /**
     * 登录失败，若要更新UI请在Handle中更新
     */
    void loginFall();

}
