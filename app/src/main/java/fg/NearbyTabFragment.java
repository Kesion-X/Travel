package fg;

import java.util.List;

import persenter.NearByList;
import persenter.NearByListImpl;

import view.MyListView;
import view.NearbyView;



import adapter.NearbyListAdapter;
import adapter.NearbyTabFragmentAdapter;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.groupfive.www.travel.R;

public class NearbyTabFragment extends BaseFragment implements MyListView.ListViewScrollListener,SwipeRefreshLayout.OnRefreshListener
,NearbyView{

	private String query="美食";
	public NearbyTabFragment(String query){
		this.query = query;

	}
	
	
	private MyListView listview; 
	private SwipeRefreshLayout mySwipeRefreshLayout;
	private NearByList nearByList;
	private Handler handler = new Handler();
	private NearbyListAdapter nearbyListAdapter;
	
	
	@Override
	public View InitView(LayoutInflater inflate) {
		// TODO Auto-generated method stub
		return inflate.inflate(R.layout.nearbytab_layout, null);
	}

	@Override
	public void InitFindView(View rootView) {
		// TODO Auto-generated method stub
		listview = (MyListView) rootView.findViewById(R.id.nearbyitem_listview);
		
	    mySwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.sf);
	    // ????????????????????????????????????
	    mySwipeRefreshLayout.setColorSchemeResources(
	    android.R.color.holo_blue_bright,
	    android.R.color.holo_green_light,
	    android.R.color.holo_orange_light,
	    android.R.color.holo_red_light);
	    mySwipeRefreshLayout.setOnRefreshListener(this);
	    listview.setListViewScrollListener(this);
	}

	@Override
	public void InitData() {
		nearByList = new NearByListImpl(this);
		
		nearByList.nearByListRefresh( query,"北京");
	}
	
	

	@Override
	public void scrollLast() {
		//handler.post(new R)
		if(nearbyListAdapter!=null)
			nearByList.nearByListLoadingMore();
	}

	@Override
	public void onRefresh() {	 
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				nearByList.nearByListRefresh( query,"北京");
				mySwipeRefreshLayout.setRefreshing(false);
			}
		}, 1000);
		

	}

	@Override
	public void refreshListSuccess(final List clonelist) {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				if(nearbyListAdapter==null){
					nearbyListAdapter = new NearbyListAdapter(getContext(),getActivity().getLayoutInflater(), clonelist);
					listview.setAdapter(nearbyListAdapter);
				}else{
					nearbyListAdapter.notifyDataSetChanged();
				}
			}
		});
		
	}

	@Override
	public void refreshListFall(final String message) {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
			}
		});

	}

	@Override
	public void upLoadListSuccess() {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				nearbyListAdapter.notifyDataSetChanged();
			}
		});
		
	}

	@Override
	public void upLoadListFall(final String message) {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
			}
		});
	}

}
