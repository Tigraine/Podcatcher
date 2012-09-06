package at.tigraine.podcatcher2.support;

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
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.R.id;
import at.tigraine.podcatcher2.R.layout;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.models.Podcast;

public class ImageAdapter extends BaseAdapter {
	
	private Context context;
	private PodcastDatabase database;
	
	public ImageAdapter(Context context, PodcastDatabase database) {
		this.context = context;
		this.database = database;
	}
	
	@Override
	public int getCount() {
		return database.getPodcasts().size();
	}

	@Override
	public Object getItem(int position) {
		return database.getPodcasts().get(position);
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
