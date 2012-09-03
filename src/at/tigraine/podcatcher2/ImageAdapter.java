package at.tigraine.podcatcher2;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	
	private Context context;
	private List<Podcast> podcasts;
	
	public ImageAdapter(Context context, List<Podcast> podcasts) {
		this.context = context;
		this.podcasts = podcasts;
	}
	
	@Override
	public int getCount() {
		return podcasts.size();
	}

	@Override
	public Object getItem(int position) {
		return podcasts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			// View is not recycled
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			view = inflater.inflate(R.layout.podcast_list_item, parent, false);
		} else {
			view = (View) convertView;
		}
		
		((TextView)view.findViewById(R.id.txtTitle)).setText(((Podcast)getItem(position)).getName());
		return view;
	}

}
