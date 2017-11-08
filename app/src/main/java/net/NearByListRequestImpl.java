package net;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bean.Detail_info;
import bean.Location;
import bean.Navi_location;
import bean.WC;

import static net.MyURL.BAIDU_NEARBY_SEARCH;
import static net.MyURL.KEY;
import static net.MyURL.PAGE_SIZE;

/**
 * Created by Administrator on 2017/11/7.
 */

public class NearByListRequestImpl implements NearByListRequest{

    private int page=0;

    private NearByListRequestListener nearByListRequestListener;

    private List list;
    private String query;
    private String regin;


    public NearByListRequestImpl(NearByListRequestListener NearByListRequestListener){
        this.nearByListRequestListener = NearByListRequestListener;
    }



    @Override
    public void nearByListRefresh(String query, String regin) {
        this.query = query;
        this.regin = regin;








        List list = new ArrayList();
        URL urlUtil;
        HttpURLConnection connection;
        Reader reader = null;
        BufferedReader bufferedReader;




        try {
            Log.d("TAG","search");
            page = 0;
            urlUtil = new URL("http://api.map.baidu.com/place/v2/search?query="+query+"&region="+regin+"&output=json&ak=sYlbsM58OMj97wDRQatSjYw8WX2G0WPE&scope=2&page_num="+page+"&page_size=10");




            connection = (HttpURLConnection) urlUtil.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            //JSON

            String line;
            StringBuffer stringBuffer = new StringBuffer();


            reader = new InputStreamReader(connection.getInputStream(), "UTF-8");

            bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);

            }


            reader.close();
            bufferedReader.close();


            Log.d("TAG","json"+stringBuffer.toString());

            if("".equals(stringBuffer)){
                this.nearByListRequestListener.nearByListRefreshFall("出错");
                return;
            }


            JSONObject jsonObject = new JSONObject(stringBuffer.toString());

            String vaString = jsonObject.getString("status");
            String ok = jsonObject.getString("message");

            JSONArray jsonArray = jsonObject.getJSONArray("results");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject personObject = (JSONObject) jsonArray.get(i);
                WC wc = new WC();
                if (personObject.isNull("name")) {
                    wc.setName("name??");
                }else {
                    wc.setName(personObject.getString("name"));
                }

                if (personObject.isNull("location")) {
                    System.out.println("location=null");
                }else {
                    wc.setLocation(new Location(personObject.getJSONObject("location").getDouble("lat"),
                            personObject.getJSONObject("location").getDouble("lng")));
                }

                if (personObject.isNull("address")) {
                    wc.setAddress("address??");
                }else {
                    wc.setAddress(personObject.getString("address"));
                }


                if (personObject.isNull("street_id")) {
                    wc.setStreet_id("");
                }else {
                    wc.setStreet_id(personObject.getString("street_id"));
                }

                if (personObject.isNull("telephone")) {
                    wc.setTelephone("telephone=null");
                }else {
                    wc.setTelephone(personObject.getString("telephone"));
                }

                if (personObject.isNull("detail")) {
                    wc.setDetail("detail=null");
                }else {
                    wc.setDetail(personObject.getString("detail"));
                }

                if (personObject.isNull("uid")) {
                    wc.setUid("uid=null");
                }else {
                    wc.setUid(personObject.getString("uid"));
                }


                if (!personObject.getJSONObject("detail_info").isNull("navi_location")) {
                    wc.setDetail_info(new Detail_info(personObject.getJSONObject("detail_info").getString("tag"),
                            new Navi_location(personObject.getJSONObject("detail_info").getJSONObject("navi_location").getDouble("lat"),
                                    personObject.getJSONObject("detail_info").getJSONObject("navi_location").getDouble("lng")),
                            BaiDuPoiPictureUtil.getBaiDuPoiPicturePath(personObject.getJSONObject("detail_info").getString("detail_url"))
                            /*personObject.getJSONObject("detail_info").getString("type")*/,

                            personObject.getJSONObject("detail_info").getString("detail_url"),
                            personObject.getJSONObject("detail_info").getString("price"),
                            personObject.getJSONObject("detail_info").getString("overall_rating"),
                            "?",
                            personObject.getJSONObject("detail_info").getString("comment_num")));
                }else{
                    wc.setDetail_info(new Detail_info(personObject.getJSONObject("detail_info").getString("tag"),
                            new Navi_location(0.0,0.0),
                            BaiDuPoiPictureUtil.getBaiDuPoiPicturePath(personObject.getJSONObject("detail_info").getString("detail_url")),
                            personObject.getJSONObject("detail_info").getString("detail_url"),
                            personObject.getJSONObject("detail_info").getString("price"),
                            personObject.getJSONObject("detail_info").getString("overall_rating"),

                            personObject.getJSONObject("detail_info").getString("groupon_num"),
                            personObject.getJSONObject("detail_info").getString("comment_num")));

                }


                list.add(wc);

            }

            this.list = list;
            this.nearByListRequestListener.nearByListRefreshSuccess(list);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            this.nearByListRequestListener.nearByListRefreshFall("???????");
            return;
        }finally {


        }
    }

    @Override
    public void nearByNowListRefresh(String query, Double lng, Double lat) {



    }

    @Override
    public void nearByListLoadingMore() {
       // List list = new ArrayList();
        URL urlUtil;
        HttpURLConnection connection;
        Reader reader = null;
        BufferedReader bufferedReader;
        try {

            page++;
            urlUtil = new URL(BAIDU_NEARBY_SEARCH+"query="+this.query+"&region="+this.regin+"&output=json&ak="+KEY+"&scope=2&page_num="+page+"&page_size="+PAGE_SIZE);

            connection = (HttpURLConnection) urlUtil.openConnection();
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            //JSON

            String line;
            StringBuffer stringBuffer = new StringBuffer();


            reader = new InputStreamReader(connection.getInputStream(), "UTF-8");

            bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);

            }


            reader.close();
            bufferedReader.close();


            if("".equals(stringBuffer)){
                this.nearByListRequestListener.nearByListLoadingMoreFall("??????");
                return;
            }


            JSONObject jsonObject = new JSONObject(stringBuffer.toString());

            String vaString = jsonObject.getString("status");
            String ok = jsonObject.getString("message");

            JSONArray jsonArray = jsonObject.getJSONArray("results");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject personObject = (JSONObject) jsonArray.get(i);
                WC wc = new WC();
                if (personObject.isNull("name")) {
                    wc.setName("name??");
                }else {
                    wc.setName(personObject.getString("name"));
                }

                if (personObject.isNull("location")) {
                    System.out.println("location=null");
                }else {
                    wc.setLocation(new Location(personObject.getJSONObject("location").getDouble("lat"),
                            personObject.getJSONObject("location").getDouble("lng")));
                }

                if (personObject.isNull("address")) {
                    wc.setAddress("address??");
                }else {
                    wc.setAddress(personObject.getString("address"));
                }


                if (personObject.isNull("street_id")) {
                    wc.setStreet_id("");
                }else {
                    wc.setStreet_id(personObject.getString("street_id"));
                }

                if (personObject.isNull("telephone")) {
                    wc.setTelephone("telephone=null");
                }else {
                    wc.setTelephone(personObject.getString("telephone"));
                }

                if (personObject.isNull("detail")) {
                    wc.setDetail("detail=null");
                }else {
                    wc.setDetail(personObject.getString("detail"));
                }

                if (personObject.isNull("uid")) {
                    wc.setUid("uid=null");
                }else {
                    wc.setUid(personObject.getString("uid"));
                }

                if (!personObject.getJSONObject("detail_info").isNull("navi_location")) {
                    wc.setDetail_info(new Detail_info(personObject.getJSONObject("detail_info").getString("tag"),
                            new Navi_location(personObject.getJSONObject("detail_info").getJSONObject("navi_location").getDouble("lat"),
                                    personObject.getJSONObject("detail_info").getJSONObject("navi_location").getDouble("lng")),
                            BaiDuPoiPictureUtil.getBaiDuPoiPicturePath(personObject.getJSONObject("detail_info").getString("detail_url"))
                            /*personObject.getJSONObject("detail_info").getString("type")*/,

                            personObject.getJSONObject("detail_info").getString("detail_url"),
                            personObject.getJSONObject("detail_info").getString("price"),
                            personObject.getJSONObject("detail_info").getString("overall_rating"),
                            "?",
                            personObject.getJSONObject("detail_info").getString("comment_num")));
                }else{
                    wc.setDetail_info(new Detail_info(personObject.getJSONObject("detail_info").getString("tag"),
                            new Navi_location(0.0,0.0),
                            BaiDuPoiPictureUtil.getBaiDuPoiPicturePath(personObject.getJSONObject("detail_info").getString("detail_url")),
                            personObject.getJSONObject("detail_info").getString("detail_url"),
                            personObject.getJSONObject("detail_info").getString("price"),
                            personObject.getJSONObject("detail_info").getString("overall_rating"),

                            personObject.getJSONObject("detail_info").getString("groupon_num"),
                            personObject.getJSONObject("detail_info").getString("comment_num")));

                }

                this.list.add(wc);

            }


            this.nearByListRequestListener.nearByListLoadingMoreSuccess();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            this.nearByListRequestListener.nearByListLoadingMoreFall("??????");
            return;
        }finally {


        }
    }

    @Override
    public void destory() {

    }
}
