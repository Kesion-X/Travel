package view;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface UserResigeView {

    /**
     * 注册成功，若要更新UI请使用Handle
     */
    public void registerSuccess();

    /**
     *注册失败，若要更新UI请使用Handle
     * @param message 失败信息
     */
    public void registerFall(String message);

}
