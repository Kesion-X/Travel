package tool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import overlayutil.OverlayManager;

/**
 * Created by Administrator on 2017/10/30.
 */

public class TabFragmentManager {

    //�ײ�����
    private List<String> TabStrList = null;
    //�ײ�Ĭ��ͼƬ
    private List<Integer> defaultDrawableIdList = null;
    //�ײ�ѡ��ͼƬ
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
     * ��ȡTabFragmentManager����
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
