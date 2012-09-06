package at.tigraine.podcatcher2.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import at.tigraine.podcatcher2.Constants;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.factory.ObjectFactory;

public class AddPodcastActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_podcast);
	}	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_podcast, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.menu_add_podcast_done:
    			addPodcast();
    			return true;
			default:
				return super.onOptionsItemSelected(item);
    	}
    }
	
	private void addPodcast() {
		Log.d(Constants.LOG_TAG, "Finished Adding Podcast");
		PodcastDatabase db = ObjectFactory.instance().createDatabase(getApplicationContext());
		db.addPodcast(((EditText)findViewById(R.id.podcast_uri)).getText().toString());
		this.setResult(Activity.RESULT_OK);
		this.finish();
	}
}
