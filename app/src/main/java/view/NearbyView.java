package view;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface NearbyView {

    /**
     * 刷新列表成功
     * @param clonelist 请拷贝一份数据
     */
    void refreshListSuccess(List clonelist);


    /**
     * 刷新列表失败
     * @param message
     */
    void refreshListFall(String message);

    /**
     * 加载更多成功
     */
    void upLoadListSuccess();

    /**
     * 加载更多失败
     * @param message
     */
    void upLoadListFall(String  message);


}
