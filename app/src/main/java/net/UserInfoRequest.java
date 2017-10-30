package net;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface UserInfoRequest {

    /**
     * 请求获取用户信息,必须运行在线程中
     * @param userAccount
     */
    void  requestUserInfo(String userAccount);

    /**
     * 释放对象
     */
    void destory();


    /**
     * 用户信息获取信息监听
     */
    interface UserInfoRequestListener{

        /**
         * 请求用户信息成功
         * @param userInfo
         */
        void  requestUserInfoSuccess(Object userInfo);

        /**
         * 请求用户信息失败
         * @param message
         */
        void  requestUserInfoFall(String message);
    }
}
