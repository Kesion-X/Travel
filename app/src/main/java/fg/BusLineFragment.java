package fg;



import android.view.LayoutInflater;
import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.groupfive.www.travel.R;

public class BusLineFragment extends BaseFragment{
	MapView mMapView = null;
	@Override
	public View InitView(LayoutInflater inflate) {
		// TODO Auto-generated method stub
		return inflate.inflate(R.layout.busline_layout, null);
	}



	@Override
	public void InitData() {
		// TODO Auto-generated method stub
		mMapView.renderMap();
	}

	@Override
	public void InitFindView(View rootView) {
		// TODO Auto-generated method stub
		mMapView = (MapView) rootView.findViewById(R.id.bmapView);
	}



	@Override
	public void onDestroy() {
		super.onDestroy();
		//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}
	@Override
	public void onResume() {
		super.onResume();
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}
	@Override
	public void onPause() {
		super.onPause();
		//在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

}
