package at.tigraine.podcatcher2.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import at.tigraine.podcatcher2.R;
import at.tigraine.podcatcher2.R.id;
import at.tigraine.podcatcher2.R.layout;
import at.tigraine.podcatcher2.R.menu;
import at.tigraine.podcatcher2.database.PodcastDatabase;
import at.tigraine.podcatcher2.factory.ObjectFactory;
import at.tigraine.podcatcher2.models.Podcast;
import at.tigraine.podcatcher2.support.ImageAdapter;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        PodcastDatabase db = ObjectFactory.instance().createDatabase(this);
        List<Podcast> podcasts = db.getPodcasts();
        ImageAdapter adapter = new ImageAdapter(this, podcasts);
       
        ListView gridview = (ListView) findViewById(R.id.podcast_grid);
        gridview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }

}