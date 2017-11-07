package view;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface NearbyDetailsCommentView {

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
