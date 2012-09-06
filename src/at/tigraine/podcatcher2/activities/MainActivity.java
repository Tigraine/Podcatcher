package at.tigraine.podcatcher2.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.factory.ObjectFactory;
import at.tigraine.podcatcher2.models.Podcast;
import at.tigraine.podcatcher2.support.ImageAdapter;

public class MainActivity extends Activity {

	public static final int ADD_PODCAST = 87954;
	public static final String LOG_TAG = "MainActivity";

	private ImageAdapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        PodcastDatabase db = ObjectFactory.instance().createDatabase(this);
        adapter = new ImageAdapter(this, db);
       
        ListView gridview = (ListView) findViewById(R.id.podcast_grid);
        gridview.setAdapter(adapter);
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.menu_add_podcast:
    			addPodcast();
    			return true;
			default:
				return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
   	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(LOG_TAG, "Received onActivityResult with resultCode = " + resultCode + " and requestCode = " + requestCode);
   		if (resultCode == Activity.RESULT_OK && requestCode == ADD_PODCAST) {
   			Toast.makeText(this, "Added Podcast successfully", Toast.LENGTH_LONG).show();
   			adapter.notifyDataSetChanged();
   		} else {
   			super.onActivityResult(requestCode, resultCode, data);
   		}
   	}

    
    private void addPodcast() {
    	Intent intent = new Intent(this, AddPodcastActivity.class);
    	startActivityForResult(intent, ADD_PODCAST);
    }

}