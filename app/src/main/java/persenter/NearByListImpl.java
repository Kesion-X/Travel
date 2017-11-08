package persenter;

import net.NearByListRequest;
import net.NearByListRequestImpl;

import java.util.List;

import view.NearbyView;

/**
 * Created by Administrator on 2017/11/7.
 */

public class NearByListImpl implements NearByList, NearByListRequest.NearByListRequestListener{


    private NearbyView NearbyView;

    private NearByListRequest nearByListRequest;

    public NearByListImpl(NearbyView nearbyView){
        this.NearbyView = nearbyView;
        this.nearByListRequest = new NearByListRequestImpl(this);
    }


    @Override
    public void nearByListRefresh(final String query, final String regin) {
        new Thread(){
            @Override
            public void run() {

                nearByListRequest.nearByListRefresh(query,regin);

            }
        }.start();
    }

    @Override
    public void nearByNowListRefresh(String query, Double lng, Double lat) {

    }

    @Override
    public void nearByListLoadingMore() {
        new Thread(){
            @Override
            public void run() {

                nearByListRequest.nearByListLoadingMore();

            }
        }.start();
    }

    @Override
    public void destory() {

    }

    @Override
    public void nearByListRefreshSuccess(List list) {
        this.NearbyView.refreshListSuccess(list);
    }

    @Override
    public void nearByListRefreshFall(String message) {
        this.NearbyView.refreshListFall(message);
    }

    @Override
    public void nearByListLoadingMoreSuccess() {
        this.NearbyView.upLoadListSuccess();
    }

    @Override
    public void nearByListLoadingMoreFall(String message) {
        this.NearbyView.upLoadListFall(message);
    }
}
