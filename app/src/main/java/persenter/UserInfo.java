package persenter;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface UserInfo {

    /**
     * 请求获取用户信息
     * @param userAccount
     */
    void  requestUserInfo(String userAccount);


    /**
     * 释放对象
     */
    void destory();

}
