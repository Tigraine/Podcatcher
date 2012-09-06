package at.tigraine.podcatcher2.activities;

import java.util.List;

import org.mcsoxford.rss.RSSFeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import at.tigraine.podcatcher2.Constants;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.database.exceptions.DatabaseException;
import at.tigraine.podcatcher2.factory.ObjectFactory;
import at.tigraine.podcatcher2.models.Podcast;
import at.tigraine.podcatcher2.support.RSSItemAdapter;

public class PodcastDetailActivity extends Activity {
	private PodcastDatabase db;
	private Podcast podcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podcast_detail);
		
		db = ObjectFactory.instance().createDatabase(this);
		Intent intent = getIntent();
		int podcastId = intent.getIntExtra(Constants.PODCAST_ID, 0);
		try {
			podcast = db.getById(podcastId);
			setTitle(podcast.getName());
			
			podcast.updateRss();
			updateUI();
			
		} catch (DatabaseException e) {
			Toast.makeText(this, "Could not find Podcast in Database", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
		
	private void updateUI() {
		RSSFeed feed = podcast.getFeed();
		String categories = join(feed.getCategories());
		((TextView)findViewById(R.id.podcast_category)).setText(categories);
		((TextView)findViewById(R.id.podcast_subtitle)).setText(feed.getDescription());
		
		RSSItemAdapter podcastAdapter = new RSSItemAdapter(this, feed.getItems());
		ListView listView = (ListView) findViewById(R.id.episode_list);
		listView.setAdapter(podcastAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(PodcastDetailActivity.this, ShowEpisodeActivity.class);
				intent.putExtra(Constants.PODCAST_ID, podcast.getId());
				intent.putExtra(Constants.EPISODE_POSITION, position);
				startActivity(intent);
			}
		});
	}

	private String join(List<String> strings) {
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string);
			builder.append(",");
		}
		return builder.toString();
	}
}
