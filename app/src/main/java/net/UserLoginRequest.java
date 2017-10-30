package net;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface UserLoginRequest {

    /**
     * 请求登录
     * @param account
     * @param pass
     */
    void  requestLogin(String account ,String pass);

    /**
     * 释放对象
     */
    void destory();


    /**
     * 登录状态监听
     */
    interface  UserLoginRequestListener{

        /**
         * 登录成功
         */
        void requestLoginSuccess();

        /**
         * 登录失败
         */
        void requestLoginFall();
    }

}
