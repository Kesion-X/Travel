package net;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface NearbyDetailsRequest {

    /**
     * 获取周边详情
     * @param uid
     */
    void nearbyDetails(String uid);

    void destory();


    interface NearbyDetailsRequestListener{
        /**
         * 获取周边详情成功
         * @param list
         */
        void nearbyDetailsSuccess(List list);

        /**
         * 获取周边详情失败
         * @param message
         */
        void nearbyDetailsFall(String message);
    }
}
