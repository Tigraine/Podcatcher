package at.tigraine.podcatcher2.database;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import at.tigraine.podcatcher2.models.Podcast;



public class FakePodcastDatabase implements PodcastDatabase {

	private static ArrayList<Podcast> podcasts = new ArrayList<Podcast>(){{
		add(new Podcast("Omega Tau"));
		add(new Podcast("Hanselminutes"));
		add(new Podcast("This Developers Life"));
		add(new Podcast("Ruby Rogues"));
		add(new Podcast("WRINT"));
	}};
	
	public FakePodcastDatabase(Context context) {

	}
	
	@Override
	public List<Podcast> getPodcasts() {
		
		return podcasts;
	}

	@Override
	public void addPodcast(String uri) {
		podcasts.add(new Podcast("Hello World"));
	}
}
