package fg;

import java.util.ArrayList;
import java.util.List;

import view.ViewIndicatorLayout;
import adapter.NearbyTabFragmentAdapter;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.groupfive.www.travel.R;


public class NearByFragment extends BaseFragment{

	
	private ViewIndicatorLayout indicatorLayout;
	private List<String> list;
	private Handler handler = new Handler(); 
	private ViewPager pager;
	private List<Fragment> fglist;
	
	@Override
	public View InitView(LayoutInflater inflate) {
		// TODO Auto-generated method stub
		return inflate.inflate(R.layout.nearby_layout, null);
	}



	@Override
	public void InitData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InitFindView(View rootView) {
		list = new ArrayList<String>();
		fglist = new ArrayList<Fragment>();
		indicatorLayout = (ViewIndicatorLayout) rootView.findViewById(R.id.viewpage_indicator);
		pager = (ViewPager) rootView.findViewById(R.id.vp);
		
		list.add("享美食");
		list.add("逛景点");
		list.add("住酒店");
		list.add("找银行");
		list.add("爱购物");

		
		fglist.add(new NearbyTabFragment("美食"));
		fglist.add(new NearbyTabFragment("景点"));
		fglist.add(new NearbyTabFragment("酒店"));
		fglist.add(new NearbyTabFragment("银行"));
		fglist.add(new NearbyTabFragment("购物"));
	
		final NearbyTabFragmentAdapter fragmentAdapter = new NearbyTabFragmentAdapter(getFragmentManager(), fglist);
		
		
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				indicatorLayout.setInitTitle((ArrayList<String>) list);
				pager.setAdapter(fragmentAdapter);
				indicatorLayout.setViewPage(pager);
			}
		});
	
		
	}

}
