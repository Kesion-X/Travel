package com.groupfive.www.travel;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import overlayutil.DrivingRouteOverlay;
import overlayutil.OverlayManager;
import overlayutil.TransitRouteOverlay;


public class TestMapActivity extends Activity implements BDLocationListener ,BaiduMap.OnMapClickListener,OnGetRoutePlanResultListener ,View.OnClickListener{
    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    private EditText searchEt;
    private Button searchBt;
    //搜索相关
    RoutePlanSearch mSearch = null;
    //全局变量
    private double myLongitude;     //经度
    private double myLatitude;  //纬度
    PlanNode stNode ;
    OverlayManager routeOverlay = null;
    PlanNode enNode ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_test_map);

        searchEt = (EditText) findViewById(R.id.search_et);
        searchBt = (Button) findViewById(R.id.search_bt);
        searchBt.setOnClickListener(this);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();

        // 开启定位图层
         mBaiduMap.setMyLocationEnabled(true);


        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(this);
        //注册监听函数

       // mM

     //   Location location = getLocation(this);

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

     //   option.setIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

    //    option.setWifiValidTime(5*60*1000);
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();

/*
        RoutePlanSearch mSearch = RoutePlanSearch.newInstance();

        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "龙泽");

        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "西单");
*/

        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);

    }



//--------------------------------生命周期管理--------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    //---------------------------------------定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            //if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
          //  }
          //  return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
           // if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
         //   }
          //  return null;
        }
    }

    //-----------------------------


    private class MyTransitRouteOverlay extends TransitRouteOverlay{

        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            //if (useDefaultIcon) {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            //  }
            //  return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            // if (useDefaultIcon) {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            //   }
            //  return null;
        }
    }


    //--------------------------------当前位置监听----------------------------------------

    private boolean isFirst = true;



    @Override
    public void onReceiveLocation(BDLocation location) {

       if(isFirst){
           // 构造定位数据
           MyLocationData locData = new MyLocationData.Builder()
                   .accuracy(location.getRadius())
                   // 此处设置开发者获取到的方向信息，顺时针0-360
                   .direction(100).latitude(location.getLatitude())
                   .longitude(location.getLongitude()).build();

           // 设置定位数据
           mBaiduMap.setMyLocationData(locData);
           LatLng ll = new LatLng(location.getLatitude(),
                   location.getLongitude());


           MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
           // 移动到某经纬度
           mBaiduMap.animateMapStatus(update);

           isFirst = false;

           // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
           BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                   .fromResource(R.drawable.icon_geo);
           MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
           mBaiduMap.setMyLocationConfiguration(config);
       }

        myLongitude = location.getLongitude();
        myLatitude = location.getLatitude();


    }


    //----------------------------------点击监听------------------------------------
    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }


    //----------------------------------路径监听-----------
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {

            if(routeOverlay!=null) {
                routeOverlay.removeFromMap();
                mBaiduMap.clear();
            }
                /* nodeIndex = -1;
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);*/
            TransitRouteLine route = result.getRouteLines().get(0);
            result.getRouteLines();
            TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaiduMap);
            routeOverlay = overlay;


            // mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
            routeOverlay.addToMap();
        }
    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult result) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {

            if(routeOverlay!=null) {
                routeOverlay.removeFromMap();
                mBaiduMap.clear();
            }
                /* nodeIndex = -1;
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);*/
            DrivingRouteLine route = result.getRouteLines().get(0);
            result.getRouteLines();
            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
            routeOverlay = overlay;


           // mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
            routeOverlay.addToMap();
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }


    //--------------------------------搜索按钮监听-----------------------------
    @Override
    public void onClick(View v) {
        Log.d("KAT","click");
        switch (v.getId()){
            case R.id.search_bt:
                Log.d("KAT","click");
                Toast.makeText(this,"click",Toast.LENGTH_LONG).show();
                stNode = PlanNode.withLocation(new LatLng(myLatitude, myLongitude));
                PlanNode enNode = PlanNode.withCityNameAndPlaceName("福州", searchEt.getText().toString());
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
               // mSearch.masstransitSearch(new MassTransitRoutePlanOption().from(stNode).to(enNode));
              //  mSearch.transitSearch(new TransitRoutePlanOption().from(stNode).to(enNode));
                break;
        }
    }
}