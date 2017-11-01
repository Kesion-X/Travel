package net;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface UserRegisterRequest {


    /**
     * 注册 此方法请在线程中执行
     * @param account
     * @param password
     */
    public void registerRequest(String account,String password);

    public void destory();

    interface UserRegisterRequestListener{

        /**
         * 注册成功
         */
        public void registerSuccess();

        /**
         *注册失败
         * @param message 失败信息
         */
        public void registerFall(String message);
    }
}
