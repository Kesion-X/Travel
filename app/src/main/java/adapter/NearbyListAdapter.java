package adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.groupfive.www.travel.GlideApp;
import com.groupfive.www.travel.R;

import net.BaiDuPoiPictureUtil;

import bean.WC;



public class NearbyListAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private List<WC> list;
	private Context context;


	public NearbyListAdapter(Context context,LayoutInflater inflater,List list){
		this.context = context;
		this.inflater = inflater;
		this.list = list;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup arg2) {
		
		
		ViewHolder viewHolder = null;
		
		if(contentView==null){
			contentView = inflater.inflate(R.layout.nearbyitem_listview, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) contentView.findViewById(R.id.nearby_item_name);
			viewHolder.score=(TextView) contentView.findViewById(R.id.nearby_item_socre);
			viewHolder.price=(TextView) contentView.findViewById(R.id.nearby_item_price);
			viewHolder.type=(TextView) contentView.findViewById(R.id.nearby_item_type);
			viewHolder.address=(TextView) contentView.findViewById(R.id.nearby_item_address);
			viewHolder.buyCount=(TextView) contentView.findViewById(R.id.nearby_item_buycount);
			viewHolder.image=(ImageView) contentView.findViewById(R.id.nearby_item_pic);
			
			contentView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) contentView.getTag();
			
		}
		viewHolder.name.setText(list.get(position).getName());
		viewHolder.score.setText(list.get(position).getDetail_info().getOverall_rating()+"");
		viewHolder.price.setText(list.get(position).getDetail_info().getPrice()+"");
		viewHolder.type.setText(list.get(position).getDetail_info().getTag());
		viewHolder.address.setText(list.get(position).getAddress());
		viewHolder.buyCount.setText(list.get(position).getDetail_info().getPrice()+"");
		//viewHolder.image.setBackgroundResource(R)
		Log.d("KAG",list.get(position).getDetail_info().getType());
		GlideApp.with(context)
				.load(list.get(position).getDetail_info().getType())
				.placeholder(R.drawable.ic_launcher_background)
				.error(R.drawable.ic_launcher_background)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.onlyRetrieveFromCache(true)
				.into(viewHolder.image);
		
		
		return contentView;
	}
	
	class ViewHolder{
		
		TextView name;
		TextView score;
		TextView price;
		TextView type;
		TextView address;
		TextView buyCount;
		ImageView image;
	}

}
