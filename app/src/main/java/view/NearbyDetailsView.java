package view;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface NearbyDetailsView {

    /**
     * 获取详情成功
     * @param details
     */
    void nearbyDetailsSuccess(Object details);


    /**
     * 获取详情失败
     * @param message
     */
    void nearbyDetailsFall(String message);




}
