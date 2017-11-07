package net;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface NearbyDetailsCommentRequest {

    /**
     * 周边详情评论
     * @param uid
     */
    void nearbyDetailsComment(String uid);

    void  destory();

    interface NearbyDetailsCommentRequestListener{
        /**
         * 获取周边详情评论成功
         * @param list
         */
        void nearbyDetailsCommentSuccess(List list);

        /**
         * 获取周边详情评论失败
         * @param message
         */
        void nearbyDetailsCommentFall(String message);
    }
}
