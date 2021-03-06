package at.tigraine.podcatcher2.support;

import java.text.SimpleDateFormat;
import java.util.List;

import org.mcsoxford.rss.RSSItem;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import at.tigraine.podcatcher2.R;

public class RSSItemAdapter extends BaseAdapter {

	private List<RSSItem> items;
	private Context context;

	public RSSItemAdapter(Context context, List<RSSItem> items) {
		this.items = items;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			// View is not recycled
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			view = inflater.inflate(R.layout.rss_list_item, parent, false);
		} else {
			view = (View) convertView;
		}
		
		RSSItem item = (RSSItem)getItem(position);
		
		/*Log.d(Constants.LOG_TAG, "Content: " + item.getContent());
		Log.d(Constants.LOG_TAG, "Description: " + item.getDescription());
		Log.d(Constants.LOG_TAG, "Link: " + item.getLink());
		Log.d(Constants.LOG_TAG, "PubDate: " + item.getPubDate());*/
		
		((TextView)view.findViewById(R.id.rss_item_title)).setText(item.getTitle());
		((TextView)view.findViewById(R.id.rss_item_description)).setText(Html.fromHtml(item.getDescription()));
		SimpleDateFormat format = new SimpleDateFormat("kk:mm d.M.y");
		String date = format.format(item.getPubDate());
		((TextView)view.findViewById(R.id.rss_item_pubdate)).setText(date);
		return view;
	}

}
