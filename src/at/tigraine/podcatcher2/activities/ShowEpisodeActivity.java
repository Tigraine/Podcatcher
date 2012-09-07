package at.tigraine.podcatcher2.activities;

import org.mcsoxford.rss.RSSItem;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import at.tigraine.podcatcher2.Constants;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.database.exceptions.DatabaseException;
import at.tigraine.podcatcher2.factory.ObjectFactory;
import at.tigraine.podcatcher2.models.Podcast;
import at.tigraine.podcatcher2.services.MediaPlayerService;

public class ShowEpisodeActivity extends Activity {

	private Podcast podcast;
	private RSSItem episode;
	private Button playButton;
	
	private Boolean playing = false;

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
		WebView webView = (WebView)findViewById(R.id.episode_content);
		if (episode.getContent() != null) {
			webView.loadData(episode.getContent(), "text/html", "UTF-8");		
		} else {
			webView.loadData(episode.getDescription(), "text/html", "UTF-8");
		}
		
		playButton = ((Button)findViewById(R.id.play_episode));
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playing = !playing;
				Intent playIntent = new Intent(ShowEpisodeActivity.this, MediaPlayerService.class);
				playIntent.putExtra(Constants.ENCLOSURE_URL, episode.getEnclosure());
				startService(playIntent);
				if (playing) {
					playButton.setText(R.string.pause);
				} else {
					playButton.setText(R.string.play_episode);
				}
			}
		});
	}
	
	private void setTextViewText(int resource, String text) {
		((TextView)findViewById(resource)).setText(text);
	}

}
