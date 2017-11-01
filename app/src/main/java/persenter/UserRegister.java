package persenter;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface UserRegister {

    /**
     * 注册
     * @param account
     * @param password
     */
    public void register(String account,String password);

    public void destory();
}
