package at.tigraine.podcatcher2;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        PodcastDatabase db = ObjectFactory.createDatabase(this);
        List<Podcast> podcasts = db.getPodcasts();
        ImageAdapter adapter = new ImageAdapter(this, podcasts);
       
        GridView gridview = (GridView) findViewById(R.id.podcast_grid);
        gridview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }

}