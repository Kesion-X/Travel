package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class NearbyTabTitle {

    private volatile static NearbyTabTitle nearbyTabTitle= null;
    private List<String> list;


    public NearbyTabTitle(){
        list = new ArrayList<>();
        init();
    }

    private void init() {

        list.add("美食");
        list.add("");
    }

    public static NearbyTabTitle getNearbyTabTitle(){

        if(nearbyTabTitle==null){

            synchronized (NearbyTabTitle.class){
                if(nearbyTabTitle==null)
                    nearbyTabTitle = new NearbyTabTitle();
            }

        }
        return nearbyTabTitle;
    }

}
