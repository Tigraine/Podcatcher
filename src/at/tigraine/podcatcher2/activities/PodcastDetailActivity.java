package at.tigraine.podcatcher2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import at.tigraine.podcatcher2.Constants;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.database.exceptions.DatabaseException;
import at.tigraine.podcatcher2.factory.ObjectFactory;
import at.tigraine.podcatcher2.models.Podcast;

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
		} catch (DatabaseException e) {
			Toast.makeText(this, "Could not find Podcast in Database", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
