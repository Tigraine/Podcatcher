package at.tigraine.podcatcher2.activities;

import org.mcsoxford.rss.RSSItem;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import at.tigraine.podcatcher2.Constants;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.database.exceptions.DatabaseException;
import at.tigraine.podcatcher2.factory.ObjectFactory;
import at.tigraine.podcatcher2.models.Podcast;

public class ShowEpisodeActivity extends Activity {

	private Podcast podcast;
	private RSSItem episode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_episode);
		
		PodcastDatabase db = ObjectFactory.instance().createDatabase(this);
		try {
			podcast = db.getById(getIntent().getIntExtra(Constants.PODCAST_ID, 0));
			int location = getIntent().getIntExtra(Constants.EPISODE_POSITION, 0);
			episode = podcast.getFeed().getItems().get(location);
			
			updateUI();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateUI() {
		setTitle(episode.getTitle());
		((WebView)findViewById(R.id.episode_content)).loadData(episode.getContent(), "text/html", "UTF-8");
	}
	
	private void setTextViewText(int resource, String text) {
		((TextView)findViewById(resource)).setText(text);
	}

}
