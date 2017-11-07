package net;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface NearByListRequest {

    /**
     *刷新附近列表
     * @param query 查询关键字 如:美食
     * @param regin 行政地区
     */
    void nearByListRefresh(String query,String regin);

    /**
     * 当前附近搜索
     * @param query 查询关键字 如:美食
     * @param lng 经度
     * @param lat 纬度
     */
    void nearByNowListRefresh(String query,Double lng,Double lat);

    /**
     * 加载更多
     */
    void nearByListLoadingMore();

    void destory();


    interface  NearByListRequestListener{

        /**
         * 刷新列表成功
         * @param list
         */
        void nearByListRefreshSuccess(List list);
        /**
         * 刷新失败
         * @param message
         */
        void nearByListRefreshSuccess(String message);

        /**
         * 加载更多成功
         */
        void nearByListLoadingMoreSuccess();

        /**
         * 加载更多失败
         */
        void nearByListLoadingMoreFall();
    }

}
