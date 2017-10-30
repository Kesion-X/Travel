package persenter;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface UserLogin {


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

}
