package fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/4/26.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * content view
     */
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  getRootView(inflater);
    }

    private View getRootView(LayoutInflater inflater) {
        if(rootView==null){
            rootView = InitView(inflater);
            InitFindView();
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if(viewGroup!=null) {
            viewGroup.removeView(rootView);
        }

        return rootView;
    }

    public View getRootView(){
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        InitData();
    }

    public abstract View InitView(LayoutInflater inflate);
    public abstract void InitFindView();
    public abstract void InitData();
}
