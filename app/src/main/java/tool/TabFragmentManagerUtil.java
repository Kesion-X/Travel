package tool;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class TabFragmentManagerUtil {

    private TabFragmentManager mTabFragmentManager;


    public TabFragmentManagerUtil(){
        mTabFragmentManager = TabFragmentManager.getmTabFragmentManager();
        initTabStrList(mTabFragmentManager.getTabStrList());
        initDefaultDrawableIdList(mTabFragmentManager.getDefaultDrawableIdList());
        initChoiceDrawableIdList(mTabFragmentManager.getChoiceDrawableIdList());
        initFragmentList(mTabFragmentManager.getFragmentList());
    }


    public List<String> getTabStrList() {
        return mTabFragmentManager.getTabStrList();
    }

    public List<Integer> getDefaultDrawableIdList() {
        return mTabFragmentManager.getDefaultDrawableIdList();
    }

    public List<Integer> getChoiceDrawableIdList() {
        return mTabFragmentManager.getChoiceDrawableIdList();
    }

    public List<Class> getFragmentList() {
        return mTabFragmentManager.getFragmentList();
    }

    public abstract void initTabStrList(List<String> list);
    public abstract void initDefaultDrawableIdList(List<Integer> list);
    public abstract void initChoiceDrawableIdList(List<Integer> list);
    public abstract void initFragmentList(List<Class> list);

}
