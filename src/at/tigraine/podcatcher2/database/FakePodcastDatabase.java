package at.tigraine.podcatcher2.database;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import at.tigraine.podcatcher2.models.Podcast;



public class FakePodcastDatabase implements PodcastDatabase {

	public FakePodcastDatabase(Context context) {
	}
	
	@Override
	public List<Podcast> getPodcasts() {
		ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
		podcasts.add(new Podcast("Omega Tau"));
		podcasts.add(new Podcast("Hanselminutes"));
		podcasts.add(new Podcast("This Developers Life"));
		podcasts.add(new Podcast("Ruby Rogues"));
		podcasts.add(new Podcast("WRINT"));
		return podcasts;
	}

}
