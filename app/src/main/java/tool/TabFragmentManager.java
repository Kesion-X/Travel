package tool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import overlayutil.OverlayManager;

/**
 * Created by Administrator on 2017/10/30.
 */

public class TabFragmentManager {

    //底部标题
    private List<String> TabStrList = null;
    //底部默认图片
    private List<Integer> defaultDrawableIdList = null;
    //底部选择图片
    private List<Integer> choiceDrawableIdList = null;
    //fragment
    private List<Class> fragmentList = null;


    private static volatile TabFragmentManager mTabFragmentManager = null;


    public TabFragmentManager(){
        TabStrList = new LinkedList<>();
        defaultDrawableIdList = new LinkedList<>();
        choiceDrawableIdList = new LinkedList<>();
        fragmentList  = new LinkedList<>();
    }

    /**
     * 获取TabFragmentManager对象
     * @return
     */
    public static TabFragmentManager getTabFragmentManager(){

        if(mTabFragmentManager==null){

            synchronized (TabFragmentManager.class){


                if(mTabFragmentManager==null)
                    mTabFragmentManager = new TabFragmentManager();
            }

        }
        return mTabFragmentManager;
    }
    
    public void clear(){
    	TabStrList.clear();
    	defaultDrawableIdList.clear();
    	choiceDrawableIdList.clear();
    	fragmentList.clear();
    }

    public List<String> getTabStrList() {
        return TabStrList;
    }

    public List<Integer> getDefaultDrawableIdList() {
        return defaultDrawableIdList;
    }

    public List<Integer> getChoiceDrawableIdList() {
        return choiceDrawableIdList;
    }

    public List<Class> getFragmentList() {
        return fragmentList;
    }

    public static TabFragmentManager getmTabFragmentManager() {
        return mTabFragmentManager;
    }


}
